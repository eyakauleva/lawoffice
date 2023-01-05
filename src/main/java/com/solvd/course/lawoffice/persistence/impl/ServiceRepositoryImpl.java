package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Service> getLawyerServices(Long lawyerId) {
        String SELECT_LAWYER_SERVICES = "select \n" +
                "services.id service_id, services.service_id service_parent_id,\n" +
                "services.name service_name, services.description service_description\n" +
                "from services \n" +
                "inner join lawyers_has_services on services.id=lawyers_has_services.service_id\n" +
                "where lawyers_has_services.lawyer_id = ?;";
        try (Connection con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
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
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }
}
