package com.solvd.course.lawoffice.persistence.jdbc.impl;

import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.persistence.UserRepository;
import com.solvd.course.lawoffice.persistence.jdbc.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

//@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSource dataSource;
    private final static String SELECT_QUERY_BY_LOGIN
            = "select id user_id, role user_role, name user_name, surname user_surname, " +
            "email user_email, phone user_phone, status user_status, login user_login, password user_password " +
            "from users where login = ?";


    @Override
    @SneakyThrows
    public Optional<User> findByLogin(String login) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_QUERY_BY_LOGIN)) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                Optional<User> user = Optional.empty();
                while (rs.next()) {
                    user = Optional.of(UserMapper.mapRow(rs));
                }
                return user;
            }
        }
    }

}
