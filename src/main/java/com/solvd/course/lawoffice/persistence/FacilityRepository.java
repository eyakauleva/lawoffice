package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Facility;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository {

    Optional<Facility> findById(Long id);

    List<Facility> findAll();

}
