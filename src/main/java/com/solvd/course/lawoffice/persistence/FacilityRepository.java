package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Facility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FacilityRepository {

    Optional<Facility> findById(Long id);

    List<Facility> findAll();

}
