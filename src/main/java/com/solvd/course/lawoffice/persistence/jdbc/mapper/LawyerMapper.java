package com.solvd.course.lawoffice.persistence.jdbc.mapper;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class LawyerMapper {

    @SneakyThrows
    public static Lawyer mapRow(ResultSet rs) {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setName(rs.getString("user_name"));
        user.setSurname(rs.getString("user_surname"));
        user.setPhone(rs.getString("user_phone"));
        user.setEmail(rs.getString("user_email"));
        user.setStatus(UserStatus.valueOf(rs.getString("user_status")));
        user.setRole(Role.valueOf(rs.getString("user_role")));
        Lawyer lawyer = new Lawyer(user);
        lawyer.setLawyerId(rs.getLong("lawyer_id"));
        lawyer.setDescription(rs.getString("lawyer_description"));
        lawyer.setExperience(rs.getFloat("lawyer_experience"));
        lawyer.setStartDate(rs.getDate("lawyer_start_date").toLocalDate());
        return lawyer;
    }

}
