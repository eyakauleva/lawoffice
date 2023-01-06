package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    public void create(Consultation consultation) {
        consultationRepository.create(consultation);
    }

    public void update(Consultation consultation) {
        consultationRepository.update(consultation);
    }

    public List<Consultation> getAll(Boolean unoccupiedOnly) {
        return consultationRepository.findAll(unoccupiedOnly);
    }
}
