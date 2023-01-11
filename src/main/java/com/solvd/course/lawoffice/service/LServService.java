package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.LServ;

import java.util.List;

public interface LServService {

    LServ findById(Long id);

    List<LServ> findAll();

}
