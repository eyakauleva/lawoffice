package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(Review review){
        review.setReviewTime(LocalDateTime.now());
        reviewRepository.saveReview(review);
    }

    public void updateReview(Review review){
        reviewRepository.updateReview(review);
    }

    public void deleteReview(Long id){
        reviewRepository.deleteReview(id);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.getAllReviews();
    }
}
