package com.solvd.course.lawoffice.persistence.jdbc.impl;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import com.solvd.course.lawoffice.persistence.jdbc.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ConditionalOnProperty(prefix = "repository", name = "impl", havingValue = "jdbc")
public class ReviewRepositoryImpl implements ReviewRepository {

    private final DataSource dataSource;

    private final static String CREATE_QUERY
            = "insert into reviews(user_id, description, grade) values(?, ?, ?);";

    private final static String UPDATE_QUERY
            = "update reviews set user_id=?, description=?, grade=?, review_time=? where id=?;";

    private final static String DELETE_QUERY = "delete from reviews where id=?;";

    private final static String SELECT_QUERY
            = "select reviews.id review_id, reviews.description review_description, " +
            "reviews.grade review_grade, reviews.review_time review_time, " +
            "users.id user_id, users.name user_name, users.surname user_surname " +
            "from reviews inner join users on users.id = reviews.user_id %s;";

    @Override
    @SneakyThrows
    public void create(Review review) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, review.getClient().getUserId());
            st.setString(2, review.getDescription());
            st.setInt(3, review.getGrade());
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    review.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void update(Review review) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, review.getClient().getUserId());
            st.setString(2, review.getDescription());
            st.setInt(3, review.getGrade());
            st.setTimestamp(4, Timestamp.valueOf(review.getReviewTime()));
            st.setLong(5, review.getId());
            st.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(DELETE_QUERY)) {
            st.setLong(1, id);
            st.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Optional<Review> findById(Long reviewId) {
        String query = String.format(SELECT_QUERY, "where reviews.id = " + reviewId);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            Optional<Review> review = Optional.empty();
            while (rs.next()) {
                review = Optional.of(ReviewMapper.mapRow(rs));
            }
            return review;
        }
    }

    @Override
    @SneakyThrows
    public List<Review> findAll() {
        String query = String.format(SELECT_QUERY, Strings.EMPTY);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(ReviewMapper.mapRow(rs));
            }
            return reviews;
        }
    }

}
