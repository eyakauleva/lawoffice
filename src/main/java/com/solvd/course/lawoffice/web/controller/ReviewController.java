package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReviewDto reviewDto){
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.create(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ReviewDto reviewDto, @PathVariable("id") Long id){
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.update(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll(){
        List<Review> reviews = reviewService.getAll();
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(reviewMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }
}
