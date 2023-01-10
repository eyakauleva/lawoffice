package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffairService {
    private final AffairRepository affairRepository;

    public Integer countByStatus(AffairCriteria criteria) {
        return affairRepository.countByStatus(criteria);
    }
}
