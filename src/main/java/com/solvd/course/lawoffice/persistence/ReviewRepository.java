package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    void create(Review review);

    void update(Review review);

    void delete(Long id);

    Optional<Review> findById(Long id);

    List<Review> findAll();

}
