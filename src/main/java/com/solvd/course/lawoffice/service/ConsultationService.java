package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import com.solvd.course.lawoffice.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    public void create(Consultation consultation) {
        consultationRepository.create(consultation);
    }

    @Transactional
    public void update(Consultation consultation) {
        Optional<Consultation> initialConsultation = consultationRepository.findById(consultation.getId());
        if(initialConsultation.isEmpty()) throw new ResourceNotFoundException("Consultation (id=" + consultation.getId() + ") does not exist");
        if(Objects.isNull(consultation.getVisitTime())) consultation.setVisitTime(initialConsultation.get().getVisitTime());
        if(Objects.isNull(consultation.getLawyer())) consultation.setLawyer(initialConsultation.get().getLawyer());
        if(Objects.isNull(consultation.getUser())) consultation.setUser(initialConsultation.get().getUser());
        consultationRepository.update(consultation);
    }

    public List<Consultation> findAll(Boolean unoccupiedOnly) {
        return consultationRepository.findAll(unoccupiedOnly);
    }
}
