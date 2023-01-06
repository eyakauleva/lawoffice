package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public void createConsultation(Consultation consultation) {
        consultationRepository.createConsultation(consultation);
    }

    public void assignToConsultation(Consultation consultation) {
        consultationRepository.assignToConsultation(consultation);
    }

    public List<Consultation> getConsultations(Boolean unoccupiedOnly) {
        return consultationRepository.getConsultations(unoccupiedOnly);
    }
}
