package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.User;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ConsultationRepositoryImpl implements ConsultationRepository {
    private final DataSource dataSource;
    private final static String CREATE_QUERY
            = "insert into consultations(lawyer_id, user_id, visit_time) values(?, ?, ?);";
    private final static String UPDATE_QUERY
            = "update consultations set lawyer_id=?, user_id=?, visit_time=? where id=?;";
    private final static String SELECT_QUERY
            = "select consultations.id consultation_id, consultations.lawyer_id consultation_lawyer_id, " +
            "consultations.visit_time consultation_visit_time, lawyers.user_id user_id, " +
            "users.name user_name, users.surname user_surname " +
            "from consultations " +
            "inner join lawyers on lawyers.id = consultations.lawyer_id " +
            "inner join users on users.id = lawyers.user_id " +
            "where consultations.user_id is %s null;";

    @Override
    public void create(Consultation consultation) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(CREATE_QUERY)) {
            st.setLong(1, consultation.getLawyer().getId());
            if (Objects.isNull(consultation.getUser())) st.setNull(2, Types.BIGINT);
            else st.setLong(2, consultation.getUser().getId());
            st.setTimestamp(3, Timestamp.valueOf(consultation.getVisitTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Consultation consultation) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, consultation.getLawyer().getId());
            st.setLong(2, consultation.getUser().getId());
            st.setTimestamp(3, Timestamp.valueOf(consultation.getVisitTime()));
            st.setLong(4, consultation.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Consultation> findAll(Boolean unoccupiedOnly) {
        String query;
        if (unoccupiedOnly)
            query = String.format(SELECT_QUERY, Strings.EMPTY);
        else query = String.format(SELECT_QUERY, "not");
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            List<Consultation> consultations = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("consultation_id");
                Long lawyerId = rs.getLong("consultation_lawyer_id");
                LocalDateTime visitTime = rs.getTimestamp("consultation_visit_time").toLocalDateTime();
                Long userId = rs.getLong("user_id");
                String name = rs.getString("user_name");
                String surname = rs.getString("user_surname");
                User user = new User(userId, name, surname);
                Lawyer lawyer = new Lawyer(lawyerId, user);
                Consultation consultation = new Consultation(id, visitTime, lawyer, null);
                consultations.add(consultation);
            }
            return consultations;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
