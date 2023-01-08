package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import com.solvd.course.lawoffice.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(Review review){
        review.setReviewTime(LocalDateTime.now());
        reviewRepository.create(review);
    }

    @Transactional
    public void update(Review review){
        Optional<Review> initialReview = reviewRepository.findById(review.getId());
        if(initialReview.isEmpty()) throw new ResourceNotFoundException("Review (id=" + review.getId() + ") does not exist");
        if(Objects.isNull(review.getDescription())) review.setDescription(initialReview.get().getDescription());
        if(Objects.isNull(review.getGrade())) review.setGrade(initialReview.get().getGrade());
        if(Objects.isNull(review.getReviewTime())) review.setReviewTime(initialReview.get().getReviewTime());
        if(Objects.isNull(review.getUser())) review.setUser(initialReview.get().getUser());
        reviewRepository.update(review);
    }

    @Transactional
    public void delete(Long id){
        Optional<Review> initialReview = reviewRepository.findById(id);
        if(initialReview.isEmpty()) throw new ResourceNotFoundException("Review (id=" + id + ") does not exist");
        reviewRepository.delete(id);
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }
}
