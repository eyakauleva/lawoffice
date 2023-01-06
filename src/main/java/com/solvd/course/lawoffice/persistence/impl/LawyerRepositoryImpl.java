package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.User;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LawyerRepositoryImpl implements LawyerRepository {
    private final DataSource dataSource;

    @Override
    public List<Lawyer> getAllLawyers() {
        String SELECT_ALL_LAWYERS = "select \n" +
                "lawyers.id lawyer_id, lawyers.description lawyer_description, lawyers.experience,\n" +
                "users.id user_id, users.role, users.name user_name, \n" +
                "\tusers.surname, users.email, users.phone, users.status\n" +
                "\tfrom lawyers \n" +
                "\tinner join users on users.id = lawyers.user_id \n" +
                "\twhere users.status='ACTIVE' and users.role='LAWYER';";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_LAWYERS)) {
            List<Lawyer> lawyers = new ArrayList<>();
            while (rs.next()) {
                Long lawyerId = rs.getLong("lawyer_id");
                String lawyerDescription = rs.getString("lawyer_description");
                Float experience = rs.getFloat("experience");
                Long userId = rs.getLong("user_id");
                String role = rs.getString("role");
                String userName = rs.getString("user_name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                User user = new User(userId, userName, surname, phone, email, status, role);
                Lawyer lawyer = new Lawyer(lawyerId, lawyerDescription, experience, user, Collections.emptyList());
                lawyers.add(lawyer);
            }
            return lawyers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
