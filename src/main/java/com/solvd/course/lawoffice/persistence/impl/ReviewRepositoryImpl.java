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

    @Override
    public void saveReview(Review review) {
        String SAVE_REVIEW = "insert into reviews(user_id, description, grade, review_time) values(?, ?, ?, ?);";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(SAVE_REVIEW)) {
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
    public void updateReview(Review review) {
        String UPDATE_REVIEW = "update reviews set user_id=?, description=?, grade=?, review_time=? where id=?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_REVIEW)) {
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
    public void deleteReview(Long id) {
        String DROP_REVIEW = "delete from reviews where id=?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(DROP_REVIEW)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Review> getAllReviews() {
        String SELECT_ALL_REVIEWS = "select reviews.*, users.name, users.surname\n" +
                "from reviews inner join users on users.id = reviews.user_id;";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_REVIEWS)) {
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String description = rs.getString("description");
                Integer grade = rs.getInt("grade");
                LocalDateTime reviewTime = rs.getTimestamp("review_time").toLocalDateTime();
                Long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
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
