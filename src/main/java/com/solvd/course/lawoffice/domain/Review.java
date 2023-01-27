package com.solvd.course.lawoffice.domain;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;
    private String description;
    private Integer grade;
    private LocalDateTime reviewTime;
    private User client;

    public Review(Review review) {
        this.id = review.getId();
        this.description = review.getDescription();
        this.grade = review.getGrade();
        this.reviewTime = review.getReviewTime();
        this.client = review.getClient();
    }

}
