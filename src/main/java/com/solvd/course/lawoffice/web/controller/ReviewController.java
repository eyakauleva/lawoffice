package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping
    public ResponseEntity<Void> saveReview(@RequestBody ReviewDto reviewDto){
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.saveReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable("id") Long id){
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.updateReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> dropReview(@PathVariable("id") Long id){
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        List<ReviewDto> reviewDtos = reviews.stream().map(reviewMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }
}
