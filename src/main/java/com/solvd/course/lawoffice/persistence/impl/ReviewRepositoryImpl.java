package com.solvd.course.lawoffice.persistence.impl;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.domain.User;
import com.solvd.course.lawoffice.domain.exception.DaoException;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final DataSource dataSource;
    private final static String CREATE_QUERY
            = "insert into reviews(user_id, description, grade, review_time) values(?, ?, ?, ?);";
    private final static String UPDATE_QUERY
            = "update reviews set user_id=?, description=?, grade=?, review_time=? where id=?;";
    private final static String DELETE_QUERY = "delete from reviews where id=?;";
    private final static String SELECT_ALL_QUERY
            = "select reviews.id review_id, reviews.description review_description, " +
            "reviews.grade review_grade, reviews.review_time review_time, " +
            "users.id user_id, users.name user_name, users.surname user_surname " +
            "from reviews inner join users on users.id = reviews.user_id;";

    @Override
    public void create(Review review) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(CREATE_QUERY)) {
            st.setLong(1, review.getUser().getId());
            st.setString(2, review.getDescription());
            st.setInt(3, review.getGrade());
            st.setTimestamp(4, Timestamp.valueOf(review.getReviewTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Review review) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, review.getUser().getId());
            st.setString(2, review.getDescription());
            st.setInt(3, review.getGrade());
            st.setTimestamp(4, Timestamp.valueOf(review.getReviewTime()));
            st.setLong(5, review.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(DELETE_QUERY)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Review> getAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_QUERY)) {
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("review_id");
                String description = rs.getString("review_description");
                Integer grade = rs.getInt("review_grade");
                LocalDateTime reviewTime = rs.getTimestamp("review_time").toLocalDateTime();
                Long userId = rs.getLong("user_id");
                String name = rs.getString("user_name");
                String surname = rs.getString("user_name");
                User user = new User(userId, name, surname);
                Review review = new Review(id, description, grade, reviewTime, user);
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
