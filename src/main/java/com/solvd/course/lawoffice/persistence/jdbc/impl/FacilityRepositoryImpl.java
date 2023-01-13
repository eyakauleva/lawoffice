package com.solvd.course.lawoffice.persistence.jdbc.impl;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.persistence.FacilityRepository;
import com.solvd.course.lawoffice.persistence.jdbc.mapper.FacilityMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FacilityRepositoryImpl implements FacilityRepository {

    private final DataSource dataSource;

    private final static String SELECT_QUERY
            = "select services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from services %s;";

    @Override
    @SneakyThrows
    public Optional<Facility> findById(Long serviceId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(String.format(SELECT_QUERY, " where services.id=" + serviceId + ""));
             ResultSet rs = st.executeQuery()) {
            Optional<Facility> facility = Optional.empty();
            while (rs.next()) {
                facility = Optional.of(FacilityMapper.mapRow(rs));
            }
            return facility;
        }
    }

    @Override
    @SneakyThrows
    public List<Facility> findAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(String.format(SELECT_QUERY, Strings.EMPTY))) {
            List<Facility> facilities = new ArrayList<>();
            while (rs.next()) {
                facilities.add(FacilityMapper.mapRow(rs));
            }
            return facilities;
        }
    }

}
