package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewRepository {

    void create(Review review);

    void update(Review review);

    void delete(Long id);

    Optional<Review> findById(Long id);

    List<Review> findAll();

}
