package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;

public interface AffairService {

    Integer countByCriteria(AffairCriteria criteria);

}
