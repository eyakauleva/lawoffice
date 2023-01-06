package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Consultation;

import java.util.List;

public interface ConsultationRepository {
    void createConsultation(Consultation consultation);
    void assignToConsultation(Consultation consultation);
    List<Consultation> getConsultations(Boolean unoccupiedOnly);
}
