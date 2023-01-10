package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
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

    @Transactional
    public Consultation create(Consultation consultation) {
        Long id = consultationRepository.create(consultation);
        consultation.setId(id);
        return consultation;
    }

    @Transactional
    public Consultation update(Consultation consultation) {
        Optional<Consultation> initialConsultation = consultationRepository.findById(consultation.getId());
        if (initialConsultation.isEmpty())
            throw new ResourceNotFoundException("Consultation (id=" + consultation.getId() + ") does not exist");
        if (Objects.isNull(consultation.getVisitTime()))
            consultation.setVisitTime(initialConsultation.get().getVisitTime());
        if (Objects.isNull(consultation.getLawyer())) consultation.setLawyer(initialConsultation.get().getLawyer());
        if (Objects.isNull(consultation.getUser())) consultation.setUser(initialConsultation.get().getUser());
        consultationRepository.update(consultation);
        return consultation;
    }

    public List<Consultation> findAll(ConsultationCriteria criteria) {
        return consultationRepository.findAll(criteria);
    }
}
