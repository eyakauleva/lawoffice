package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Review;

import java.util.List;

public interface ReviewRepository {
    void saveReview(Review review);
    void updateReview(Review review);
    void deleteReview(Long id);
    List<Review> getAllReviews();
}
