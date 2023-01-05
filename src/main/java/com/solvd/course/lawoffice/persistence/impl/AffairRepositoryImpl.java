package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;

@Repository
public class AffairRepositoryImpl implements AffairRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AffairRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer countSuccessAffairs() {
        return countAffairsByStatus(AffairStatus.SUCCESS);
    }

    @Override
    public Integer countFailureAffairs() {
        return countAffairsByStatus(AffairStatus.FAILURE);
    }

    private Integer countAffairsByStatus(AffairStatus status){
        try (Connection con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
             PreparedStatement st = con.prepareStatement("select * from affairs where status = ?")) {
            st.setString(1, status.getValue());
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("affairsCount");
            }
            rs.close();
            return count;
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }
}
