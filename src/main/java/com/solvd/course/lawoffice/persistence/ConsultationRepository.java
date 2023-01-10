package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {
    Long create(Consultation consultation);
    void update(Consultation consultation);
    Optional<Consultation> findById(Long id);
    List<Consultation> findAll(ConsultationCriteria criteria);
}
