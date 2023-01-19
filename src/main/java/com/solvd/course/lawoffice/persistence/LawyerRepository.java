package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Lawyer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LawyerRepository {

    Optional<Lawyer> findById(Long id);

    List<Lawyer> findAll();

}
