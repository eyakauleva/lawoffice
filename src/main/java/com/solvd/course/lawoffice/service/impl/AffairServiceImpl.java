package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import com.solvd.course.lawoffice.service.AffairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffairServiceImpl implements AffairService {

    private final AffairRepository affairRepository;

    public Integer countByCriteria(AffairCriteria criteria) {
        return affairRepository.countByCriteria(criteria);
    }

}
