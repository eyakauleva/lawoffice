package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto create(@RequestBody @Validated({CreateGroup.class, ComplexTypeGroup.class}) ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.create(review);
        return reviewMapper.domainToDto(review);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto update(@RequestBody @Validated(ComplexTypeGroup.class) ReviewDto reviewDto, @PathVariable("id") Long id) {
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.update(review);
        return reviewMapper.domainToDto(review);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewService.findAll();
        return reviewMapper.domainToDto(reviews);
    }

}
