package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Lawyer;

import java.util.List;

public interface LawyerRepository {
    List<Lawyer> getAllLawyers();
}
