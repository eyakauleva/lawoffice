package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.consultation.ValidationException;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
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
        validate(consultation);
        consultationRepository.create(consultation);
        return consultation;
    }

    @Transactional
    public Consultation update(Consultation consultation) {
        Consultation initialConsultation = consultationRepository.findById(consultation.getId())
                .orElseThrow(() -> new ResourceDoesNotExistException("Consultation (id=" + consultation.getId() + ") does not exist"));
        boolean doesContainNewData = false;
        if (Objects.nonNull(consultation.getVisitTime())) {
            initialConsultation.setVisitTime(consultation.getVisitTime());
            doesContainNewData = true;
        }
        if (Objects.nonNull(consultation.getLawyer())) {
            initialConsultation.setLawyer(consultation.getLawyer());
            doesContainNewData = true;
        }
        if (Objects.nonNull(consultation.getClient())) {
            initialConsultation.setClient(consultation.getClient());
            doesContainNewData = true;
        }
        if (doesContainNewData) {
            validate(initialConsultation);
            consultationRepository.update(initialConsultation);
        }
        return initialConsultation;
    }

    public List<Consultation> findAllByCriteria(ConsultationCriteria criteria) {
        return consultationRepository.findAllByCriteria(criteria);
    }

    private void validate(Consultation consultation) {
        if (Objects.nonNull(consultation.getClient()) && Objects.isNull(consultation.getClient().getUserId())) {
            throw new ValidationException("Validation error", "consultation.client.userId", "Client's id cannot be null");
        }
        ConsultationCriteria criteria = new ConsultationCriteria();
        criteria.setVisitTime(consultation.getVisitTime());
        criteria.setLawyerId(consultation.getLawyer().getLawyerId());
        if (findAllByCriteria(criteria).size() > 0) {
            throw new UniqueConstraintViolationException("Lawyer already has consultation at this time");
        }
        if (Objects.nonNull(consultation.getClient())) {
            criteria.setLawyerId(null);
            criteria.setClientId(consultation.getClient().getUserId());
            if (findAllByCriteria(criteria).size() > 0) {
                throw new UniqueConstraintViolationException("Client already has consultation at this time");
            }
        }
    }

}
