package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.persistence.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
public class ServiceRepositoryImpl implements ServiceRepository {
    private final DataSource dataSource;
    private final static String SELECT_ALL_QUERY
            = "select services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from services " +
            "inner join lawyers_has_services on services.id=lawyers_has_services.service_id;";
    private final static String SELECT_BY_ID_QUERY
            = "select services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from services " +
            "inner join lawyers_has_services on services.id=lawyers_has_services.service_id " +
            "where services.id = ?;";

    @Override
    @SneakyThrows
    public Optional<Service> findById(Long serviceId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            st.setLong(1, serviceId);
            ResultSet rs = st.executeQuery();
            Optional<Service> service = Optional.empty();
            while (rs.next()) {
                Long id = rs.getLong("service_id");
                Long serviceParentId = rs.getLong("service_parent_id");
                String name = rs.getString("service_name");
                String description = rs.getString("service_description");
                service = Optional.of(new Service(id, name, description, new Service(serviceParentId)));
            }
            rs.close();
            return service;
        }
    }

    @Override
    @SneakyThrows
    public List<Service> findAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_QUERY)) {
            List<Service> services = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("service_id");
                Long serviceParentId = rs.getLong("service_parent_id");
                String name = rs.getString("service_name");
                String description = rs.getString("service_description");
                Service service = new Service(id, name, description, new Service(serviceParentId));
                services.add(service);
            }
            return services;
        }
    }
}
