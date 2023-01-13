package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import com.solvd.course.lawoffice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review create(Review review) {
        reviewRepository.create(review);
        return review;
    }

    @Transactional
    public Review update(Review review) {
        Review initialReview = reviewRepository.findById(review.getId())
                .orElseThrow(() -> new ResourceDoesNotExistException("Review (id=" + review.getId() + ") does not exist"));
        boolean doesContainNewData = false;
        if (Objects.nonNull(review.getDescription())) {
            initialReview.setDescription(review.getDescription());
            doesContainNewData = true;
        }
        if (Objects.nonNull(review.getGrade())) {
            initialReview.setGrade(review.getGrade());
            doesContainNewData = true;
        }
        if (Objects.nonNull(review.getClient())) {
            initialReview.setClient(review.getClient());
            doesContainNewData = true;
        }
        if(doesContainNewData){
            reviewRepository.update(initialReview);
        }
        return initialReview;
    }

    @Transactional
    public void delete(Long id) {
        reviewRepository.delete(id);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

}
