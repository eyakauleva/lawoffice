package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsultationRepository {

    void create(Consultation consultation);

    void update(Consultation consultation);

    Optional<Consultation> findById(Long id);

    List<Consultation> findAllByCriteria(ConsultationCriteria criteria);

}
