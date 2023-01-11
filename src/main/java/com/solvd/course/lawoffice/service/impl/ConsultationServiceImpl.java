package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import com.solvd.course.lawoffice.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Transactional
    public Consultation create(Consultation consultation) {
        consultationRepository.checkConsultationOnUniqueConstraints(consultation);
        consultationRepository.create(consultation);
        return consultation;
    }

    @Transactional
    public Consultation update(Consultation consultation) {
        Consultation initialConsultation = consultationRepository.findById(consultation.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation (id=" + consultation.getId() + ") does not exist"));
        if (Objects.nonNull(consultation.getVisitTime())) {
            initialConsultation.setVisitTime(consultation.getVisitTime());
        }
        if (Objects.nonNull(consultation.getLawyer())) {
            initialConsultation.setLawyer(consultation.getLawyer());
        }
        if (Objects.nonNull(consultation.getClient())) {
            initialConsultation.setClient(consultation.getClient());
        }
        consultationRepository.update(consultation);
        return consultation;
    }

    public List<Consultation> findAll(ConsultationCriteria criteria) {
        return consultationRepository.findAll(criteria);
    }

}
