package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<ReviewDto> create(@RequestBody @Validated({CreateGroup.class, ComplexTypeGroup.class}) ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.create(review);
        return new ResponseEntity<>(reviewMapper.domainToDto(review), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ReviewDto> update(@RequestBody @Validated(ComplexTypeGroup.class) ReviewDto reviewDto, @PathVariable("id") Long id) {
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.update(review);
        return new ResponseEntity<>(reviewMapper.domainToDto(review), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> findAll() {
        List<Review> reviews = reviewService.findAll();
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(reviewMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }
}
