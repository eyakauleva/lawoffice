package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.User;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class LawyerRepositoryImpl implements LawyerRepository {
    private final DataSource dataSource;
    private final static String SELECT_ALL_QUERY
            = "select lawyers.id lawyer_id, lawyers.description lawyer_description, " +
            "lawyers.experience lawyer_experience, users.id user_id, users.role user_role, " +
            "users.name user_name, users.surname user_surname, users.email user_email, " +
            "users.phone user_phone, users.status user_status, " +
            "services.id service_id, services.service_id service_parent_id, " +
            "services.name service_name, services.description service_description " +
            "from lawyers " +
            "inner join users on users.id = lawyers.user_id " +
            "inner join lawyers_has_services on lawyers.id=lawyers_has_services.lawyer_id " +
            "inner join services on lawyers_has_services.service_id = services.id;";

    @Override
    @SneakyThrows
    public List<Lawyer> findAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_QUERY)) {
            List<Lawyer> lawyers = new ArrayList<>();
            Long id = null;
            Lawyer lawyer = null;
            List<LServ> LServs = new ArrayList<>();
            while (rs.next()) {
                Long lawyerId = rs.getLong("lawyer_id");
                if (Objects.isNull(id) || !id.equals(lawyerId)) {
                    id = lawyerId;
                    if (Objects.nonNull(lawyer)) {
                        lawyer.setLServs(LServs);
                        lawyers.add(lawyer);
                    }
                    LServs = new ArrayList<>();
                    String lawyerDescription = rs.getString("lawyer_description");
                    Float experience = rs.getFloat("lawyer_experience");
                    Long userId = rs.getLong("user_id");
                    String role = rs.getString("user_role");
                    String userName = rs.getString("user_name");
                    String surname = rs.getString("user_surname");
                    String email = rs.getString("user_email");
                    String phone = rs.getString("user_phone");
                    String status = rs.getString("user_status");
                    User user = new User(userId, userName, surname, phone, email, status, role);
                    lawyer = new Lawyer(lawyerId, lawyerDescription, experience, user);
                }
                Long serviceId = rs.getLong("service_id");
                Long serviceParentId = rs.getLong("service_parent_id");
                String serviceName = rs.getString("service_name");
                String serviceDescription = rs.getString("service_description");
                LServ LServ = new LServ(serviceId, serviceName, serviceDescription, new LServ(serviceParentId));
                LServs.add(LServ);
            }
            return lawyers;
        }
    }
}
