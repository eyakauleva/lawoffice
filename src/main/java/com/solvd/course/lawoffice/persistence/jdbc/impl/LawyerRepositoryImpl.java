package com.solvd.course.lawoffice.persistence.jdbc.impl;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import com.solvd.course.lawoffice.persistence.jdbc.mapper.FacilityMapper;
import com.solvd.course.lawoffice.persistence.jdbc.mapper.LawyerMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class LawyerRepositoryImpl implements LawyerRepository {

    private final DataSource dataSource;

    private final static String SELECT_QUERY
            = "select lawyers.id lawyer_id, lawyers.description lawyer_description, " +
            "lawyers.experience lawyer_experience, lawyers.start_date lawyer_start_date, " +
            "users.id user_id, users.role user_role, " +
            "users.name user_name, users.surname user_surname, users.email user_email, " +
            "users.phone user_phone, users.status user_status, " +
            "services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from lawyers " +
            "inner join users on users.id = lawyers.user_id " +
            "inner join lawyers_has_services on lawyers.id=lawyers_has_services.lawyer_id " +
            "inner join services on lawyers_has_services.service_id = services.id %s;";

    @Override
    @SneakyThrows
    public Optional<Lawyer> findById(Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(String.format(SELECT_QUERY, " where lawyers.id = " + id));
             ResultSet rs = st.executeQuery()) {
            Optional<Lawyer> lawyer = Optional.empty();
            List<Facility> facilities = new ArrayList<>();
            while (rs.next()) {
                if (lawyer.isEmpty()) {
                    lawyer = Optional.of(LawyerMapper.mapRow(rs));
                }
                facilities.add(FacilityMapper.mapRow(rs));
            }
            lawyer.ifPresent(value -> value.setFacilities(facilities));
            return lawyer;
        }
    }

    @Override
    @SneakyThrows
    public List<Lawyer> findAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(String.format(SELECT_QUERY, Strings.EMPTY))) {
            List<Lawyer> lawyers = new ArrayList<>();
            while (rs.next()) {
                boolean doesListContainLawyer = false;
                Long lawyerId = rs.getLong("lawyer_id");
                for (Lawyer lawyer : lawyers) {
                    if (lawyer.getLawyerId().equals(lawyerId)) {
                        List<Facility> facilities = new ArrayList<>(lawyer.getFacilities());
                        facilities.add(FacilityMapper.mapRow(rs));
                        lawyer.setFacilities(facilities);
                        doesListContainLawyer = true;
                    }
                }
                if (!doesListContainLawyer) {
                    Lawyer newLawyer = LawyerMapper.mapRow(rs);
                    newLawyer.setFacilities(Collections.singletonList(FacilityMapper.mapRow(rs)));
                    lawyers.add(newLawyer);
                }
            }
            return lawyers;
        }
    }

}
