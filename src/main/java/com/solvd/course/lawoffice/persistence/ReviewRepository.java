package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Review;

import java.util.List;

public interface ReviewRepository {
    void create(Review review);
    void update(Review review);
    void delete(Long id);
    List<Review> getAll();
}
