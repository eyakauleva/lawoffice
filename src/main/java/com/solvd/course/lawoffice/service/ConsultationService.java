package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;

import java.util.List;

public interface ConsultationService {

    Consultation create(Consultation consultation);

    Consultation update(Consultation consultation);

    List<Consultation> findAllByCriteria(ConsultationCriteria criteria);

}
