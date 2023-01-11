package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Facility;

import java.util.List;

public interface FacilityService {

    Facility findById(Long id);

    List<Facility> findAll();

}
