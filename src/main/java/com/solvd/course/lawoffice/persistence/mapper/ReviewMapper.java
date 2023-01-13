package com.solvd.course.lawoffice.persistence.mapper;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.domain.user.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class ReviewMapper {

    @SneakyThrows
    public static Review mapRow(ResultSet rs) {
        User client = new User();
        client.setName(rs.getString("user_name"));
        client.setSurname(rs.getString("user_surname"));
        Review review = new Review();
        review.setId(rs.getLong("review_id"));
        review.setDescription(rs.getString("review_description"));
        review.setGrade(rs.getInt("review_grade"));
        review.setReviewTime(rs.getTimestamp("review_time").toLocalDateTime());
        review.setClient(client);
        return review;
    }

}
