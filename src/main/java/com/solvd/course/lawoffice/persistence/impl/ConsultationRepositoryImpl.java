package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.consultation.ConsultationUniqueConstraint;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import com.solvd.course.lawoffice.persistence.mapper.ConsultationMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            "consultations.visit_time consultation_visit_time, consultations.user_id consultation_client_id, " +
            "lawyers.user_id lawyer_user_id, users.name lawyer_user_name, users.surname lawyer_user_surname " +
            "from consultations " +
            "inner join lawyers on lawyers.id = consultations.lawyer_id " +
            "inner join users on users.id = lawyers.user_id %s;";


    private final static String CALL_CHECK_CONSULTATION_ON_UNIQUE_CONSTRAINTS_PROCEDURE
            = "call check_consultation_on_unique_constraints(?, ?, ?, ?);";

    @Override
    @SneakyThrows
    public void create(Consultation consultation) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, consultation.getLawyer().getLawyerId());
            if (Objects.isNull(consultation.getClient())) {
                st.setNull(2, Types.BIGINT);
            } else {
                st.setLong(2, consultation.getClient().getUserId());
            }
            st.setTimestamp(3, Timestamp.valueOf(consultation.getVisitTime()));
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    consultation.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void update(Consultation consultation) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, consultation.getLawyer().getLawyerId());
            if (Objects.isNull(consultation.getClient())) {
                st.setNull(2, Types.BIGINT);
            } else {
                st.setLong(2, consultation.getClient().getUserId());
            }
            st.setTimestamp(3, Timestamp.valueOf(consultation.getVisitTime()));
            st.setLong(4, consultation.getId());
            st.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Optional<Consultation> findById(Long consultationId) {
        String query = String.format(SELECT_QUERY, " where consultations.id = " + consultationId);
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {
            Optional<Consultation> consultation = Optional.empty();
            while (rs.next()) {
                consultation = Optional.of(ConsultationMapper.mapRow(rs));
            }
            return consultation;
        }
    }

    @Override
    @SneakyThrows
    public List<Consultation> findAll(ConsultationCriteria criteria) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(prepareQuery(criteria));
             ResultSet rs = st.executeQuery()) {
            List<Consultation> consultations = new ArrayList<>();
            while (rs.next()) {
                consultations.add(ConsultationMapper.mapRow(rs));
            }
            return consultations;
        }
    }

    @SneakyThrows
    public void checkConsultationOnUniqueConstraints(Consultation consultation) {
        try (Connection con = dataSource.getConnection();
             CallableStatement st = con.prepareCall(CALL_CHECK_CONSULTATION_ON_UNIQUE_CONSTRAINTS_PROCEDURE)) {
            //st.setLong(1, consultation.getLawyer().getLawyerId());
            if (Objects.isNull(consultation.getLawyer())) st.setNull(1, Types.BIGINT);
            else st.setLong(1, consultation.getLawyer().getLawyerId());
            if (Objects.isNull(consultation.getClient())) st.setNull(2, Types.BIGINT);
            else st.setLong(2, consultation.getClient().getUserId());
            st.setTimestamp(3, Timestamp.valueOf(consultation.getVisitTime()));
            st.registerOutParameter(4, Types.INTEGER);
            st.executeUpdate();
            int result_code = st.getInt(4);
            if (ConsultationUniqueConstraint.LAWYER_BUSY.getValue() == result_code) {
                throw new UniqueConstraintViolationException("Lawyer already has consultation at this time");
            } else if (ConsultationUniqueConstraint.USER_BUSY.getValue() == result_code) {
                throw new UniqueConstraintViolationException("User already has consultation at this time");
            }
        }
    }

    private String prepareQuery(ConsultationCriteria criteria) {
        String query = Strings.EMPTY;
        if (Objects.nonNull(criteria.getUnoccupiedOnly()) && criteria.getUnoccupiedOnly().equals(Boolean.TRUE))
            query = " where consultations.user_id is null";
        return String.format(SELECT_QUERY, query);
    }

}
