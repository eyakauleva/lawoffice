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

    @Override
    public List<Service> getLawyerServices(Long lawyerId) {
        String SELECT_LAWYER_SERVICES = "select \n" +
                "services.id service_id, services.service_id service_parent_id,\n" +
                "services.name service_name, services.description service_description\n" +
                "from services \n" +
                "inner join lawyers_has_services on services.id=lawyers_has_services.service_id\n" +
                "where lawyers_has_services.lawyer_id = ?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_LAWYER_SERVICES)) {
            st.setLong(1, lawyerId);
            ResultSet rs = st.executeQuery();
            List<Service> services = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("service_id");
                Long serviceParentId = rs.getLong("service_parent_id");
                String name = rs.getString("service_name");
                String description = rs.getString("service_description");
                Service service = new Service(id, name, description, new Service(serviceParentId));
                services.add(service);
            }
            rs.close();
            return services;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Service> getAllServices() {
        String SELECT_ALL_SERVICES = "select \n" +
                "services.id service_id, services.service_id service_parent_id,\n" +
                "services.name service_name, services.description service_description\n" +
                "from services \n" +
                "inner join lawyers_has_services on services.id=lawyers_has_services.service_id;";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_SERVICES)) {
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
    public Service getServiceById(Long serviceId) {
        String SELECT_LAWYER_SERVICES = "select \n" +
                "services.id service_id, services.service_id service_parent_id,\n" +
                "services.name service_name, services.description service_description\n" +
                "from services \n" +
                "inner join lawyers_has_services on services.id=lawyers_has_services.service_id\n" +
                "where services.id = ?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_LAWYER_SERVICES)) {
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
