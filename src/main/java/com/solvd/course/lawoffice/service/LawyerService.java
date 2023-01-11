package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;

import java.util.List;

public interface LawyerService {

    Lawyer findById(Long id);

    List<Lawyer> findAll();

}
