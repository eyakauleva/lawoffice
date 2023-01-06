package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Service> getAll() {
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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Service getById(Long serviceId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            st.setLong(1, serviceId);
            ResultSet rs = st.executeQuery();
            Service service = new Service();
            while (rs.next()) {
                Long id = rs.getLong("service_id");
                Long serviceParentId = rs.getLong("service_parent_id");
                String name = rs.getString("service_name");
                String description = rs.getString("service_description");
                service = new Service(id, name, description, new Service(serviceParentId));
            }
            rs.close();
            return service;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
