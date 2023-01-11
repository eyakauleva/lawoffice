package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Lawyer;

import java.util.List;
import java.util.Optional;

public interface LawyerRepository {

    Optional<Lawyer> findById(Long id);

    List<Lawyer> findAll();

}
