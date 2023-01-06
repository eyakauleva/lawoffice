package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class AffairRepositoryImpl implements AffairRepository {
    private final DataSource dataSource;
    private final static String SELECT_BY_STATUS_QUERY
            = "select count(*) as affairs_count from affairs where status = ?";

    @Override
    public Integer countByStatus(AffairStatus status) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_STATUS_QUERY)) {
            st.setString(1, status.getValue());
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("affairs_count");
            }
            rs.close();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
