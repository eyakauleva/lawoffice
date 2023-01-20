package com.solvd.course.lawoffice.persistence.jdbc.mapper;

import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class UserMapper {

    @SneakyThrows
    public static User mapRow(ResultSet rs) {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setRole(Role.valueOf(rs.getString("user_role")));
        user.setLogin(rs.getString("user_login"));
        user.setPassword(rs.getString("user_password"));
        user.setName(rs.getString("user_name"));
        user.setSurname(rs.getString("user_surname"));
        user.setEmail(rs.getString("user_email"));
        user.setPhone(rs.getString("user_phone"));
        user.setStatus(UserStatus.valueOf(rs.getString("user_status")));
        return user;
    }

}
