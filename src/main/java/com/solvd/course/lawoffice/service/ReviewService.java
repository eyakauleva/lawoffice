package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(Review review){
        review.setReviewTime(LocalDateTime.now());
        reviewRepository.create(review);
    }

    public void update(Review review){
        reviewRepository.update(review);
    }

    public void delete(Long id){
        reviewRepository.delete(id);
    }

    public List<Review> getAll(){
        return reviewRepository.getAll();
    }
}
