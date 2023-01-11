package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    Review update(Review review);

    void delete(Long id);

    List<Review> findAll();

}
