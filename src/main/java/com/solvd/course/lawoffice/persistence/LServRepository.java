package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.LServ;

import java.util.List;
import java.util.Optional;

public interface LServRepository {

    Optional<LServ> findById(Long id);

    List<LServ> findAll();

}
