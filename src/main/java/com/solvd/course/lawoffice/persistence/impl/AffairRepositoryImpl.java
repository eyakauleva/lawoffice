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

    @Override
    public Integer countSuccessAffairs() {
        return countAffairsByStatus(AffairStatus.SUCCESS);
    }

    @Override
    public Integer countFailureAffairs() {
        return countAffairsByStatus(AffairStatus.FAILURE);
    }

    private Integer countAffairsByStatus(AffairStatus status) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement("select * from affairs where status = ?")) {
            st.setString(1, status.getValue());
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("affairsCount");
            }
            rs.close();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
