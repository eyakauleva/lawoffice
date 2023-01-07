package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    Optional<Service> findById(Long id);
    List<Service> findAll();
}
