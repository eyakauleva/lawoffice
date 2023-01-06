package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Consultation;

import java.util.List;

public interface ConsultationRepository {
    void create(Consultation consultation);
    void update(Consultation consultation);
    List<Consultation> findAll(Boolean unoccupiedOnly);
}
