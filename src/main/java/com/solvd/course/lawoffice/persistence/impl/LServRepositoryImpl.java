package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.persistence.LServRepository;
import com.solvd.course.lawoffice.persistence.mapper.LServMapper;
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
public class LServRepositoryImpl implements LServRepository {

    private final DataSource dataSource;

    private final static String SELECT_QUERY
            = "select services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from services %s;";

    @Override
    @SneakyThrows
    public Optional<LServ> findById(Long serviceId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(String.format(SELECT_QUERY, " where services.id=" + serviceId + ""));
             ResultSet rs = st.executeQuery()) {
            Optional<LServ> service = Optional.empty();
            while (rs.next()) {
                service = Optional.of(LServMapper.mapRow(rs));
            }
            return service;
        }
    }

    @Override
    @SneakyThrows
    public List<LServ> findAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(String.format(SELECT_QUERY, Strings.EMPTY))) {
            List<LServ> services = new ArrayList<>();
            while (rs.next()) {
                services.add(LServMapper.mapRow(rs));
            }
            return services;
        }
    }

}
