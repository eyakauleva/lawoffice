package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AffairRepositoryImpl implements AffairRepository {
    private final DataSource dataSource;
    private final static String SELECT_QUERY
            = "select count(1) as affairs_count from affairs %s";

    @Override
    @SneakyThrows
    public Integer countByStatus(AffairCriteria criteria) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(prepareQuery(criteria))) {
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("affairs_count");
            }
            return count;
        }
    }

    private String prepareQuery(AffairCriteria criteria) {
        String SELECT_BY_STATUS_QUERY
                = "select count(1) as affairs_count from affairs %s";
        String query = Strings.EMPTY;
        if (Objects.nonNull(criteria.getStatus())) query = " where status = '" + criteria.getStatus() + "'";
        return String.format(SELECT_BY_STATUS_QUERY, query);
    }
}
