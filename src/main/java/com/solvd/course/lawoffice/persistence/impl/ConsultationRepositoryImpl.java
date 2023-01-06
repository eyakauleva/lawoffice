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

    @Override
    public void createConsultation(Consultation consultation) {
        String SAVE_CONSULTATION = "insert into consultations(lawyer_id, user_id, visit_time) values(?, ?, ?);";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SAVE_CONSULTATION)) {
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
    public void assignToConsultation(Consultation consultation) {
        String UPDATE_CONSULTATION = "update consultations set user_id=? where id=?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_CONSULTATION)) {
            st.setLong(1, consultation.getUser().getId());
            st.setLong(2, consultation.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Consultation> getConsultations(Boolean unoccupiedOnly) {
        String SELECT_UNOCCUPIED_CONSULTATIONS = "select consultations.*, users.name, users.surname\n" +
                "from consultations \n" +
                "inner join lawyers on lawyers.id = consultations.lawyer_id\n" +
                "inner join users on users.id = lawyers.user_id\n" +
                "where consultations.user_id is %s null;";
        if (unoccupiedOnly)
            SELECT_UNOCCUPIED_CONSULTATIONS = String.format(SELECT_UNOCCUPIED_CONSULTATIONS, Strings.EMPTY);
        else SELECT_UNOCCUPIED_CONSULTATIONS = String.format(SELECT_UNOCCUPIED_CONSULTATIONS, "not");
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_UNOCCUPIED_CONSULTATIONS)) {
            List<Consultation> consultations = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long lawyerId = rs.getLong("lawyer_id");
                LocalDateTime visitTime = rs.getTimestamp("visit_time").toLocalDateTime();
                Long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
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
