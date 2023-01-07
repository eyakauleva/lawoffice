package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Consultation;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {
    void create(Consultation consultation);
    void update(Consultation consultation);
    Optional<Consultation> findById(Long id);
    List<Consultation> findAll(Boolean unoccupiedOnly);
}
