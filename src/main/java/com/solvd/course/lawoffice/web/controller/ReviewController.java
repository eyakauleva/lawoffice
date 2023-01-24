package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import com.solvd.course.lawoffice.web.security.Role;
import com.solvd.course.lawoffice.web.validation.ClientIdRequiredGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    @PreAuthorize("isReviewCreator(#reviewDto)")
    public ReviewDto create(@RequestBody @Validated({CreateGroup.class, ClientIdRequiredGroup.class}) ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.create(review);
        return reviewMapper.domainToDto(review);
    }

    @PatchMapping(value = "/{id}")
    @PostAuthorize("isReviewOwner()")
    public ReviewDto update(@RequestBody @Validated(ClientIdRequiredGroup.class) ReviewDto reviewDto,
                            @PathVariable("id") Long id) {
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.update(review);
        return reviewMapper.domainToDto(review);
    }

    @DeleteMapping(value = "/{id}")
    @PostAuthorize("isReviewOwner() || hasRole(" + Role.ADMIN + ")")
    public void delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
    }

    @GetMapping
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewService.findAll();
        return reviewMapper.domainToDto(reviews);
    }

}
