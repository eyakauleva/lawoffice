package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;

public interface AffairRepository {
    Integer countByStatus(AffairCriteria criteria);
}
