package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.domain.exception.ValidationException;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(CreateGroup.class) ReviewDto reviewDto) {
        if(Objects.isNull(reviewDto.getUser().getId())) throw new ValidationException("Review must contain a client's id");
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.create(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ReviewDto reviewDto, @PathVariable("id") Long id) {
        if(Objects.nonNull(reviewDto.getUser()) && Objects.isNull(reviewDto.getUser().getId())) throw new ValidationException("Review must contain a client's id");
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        reviewService.update(review);
        return new ResponseEntity<>(HttpStatus.OK);
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
