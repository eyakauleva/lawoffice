package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.service.ReviewService;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import com.solvd.course.lawoffice.web.mapper.ReviewMapper;
import com.solvd.course.lawoffice.web.security.Role;
import com.solvd.course.lawoffice.web.validation.ClientIdRequiredGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Methods to interact with reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    @PreAuthorize("isReviewCreator(#reviewDto)")
    @Operation(summary = "Create new review")
    public ReviewDto create(@RequestBody @Validated({CreateGroup.class, ClientIdRequiredGroup.class}) @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Review to create") ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.create(review);
        return reviewMapper.domainToDto(review);
    }

    @PatchMapping(value = "/{id}")
    @PostAuthorize("isReviewOwner()")
    @Operation(summary = "Update existing review")
    public ReviewDto update(@RequestBody @Validated(ClientIdRequiredGroup.class) @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Review fields to update") ReviewDto reviewDto,
                            @PathVariable("id") @Parameter(description = "Id of the review to update") Long id) {
        reviewDto.setId(id);
        Review review = reviewMapper.dtoToDomain(reviewDto);
        review = reviewService.update(review);
        return reviewMapper.domainToDto(review);
    }

    @DeleteMapping(value = "/{id}")
    @PostAuthorize("isReviewOwner() || hasRole(" + Role.ADMIN + ")")
    @Operation(summary = "Delete review by id")
    public void delete(@PathVariable("id") @Parameter(description = "Id of the review to delete") Long id) {
        reviewService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Find all reviews")
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewService.findAll();
        return reviewMapper.domainToDto(reviews);
    }

}
