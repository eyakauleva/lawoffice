package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;

public interface AffairRepository {
    Integer countByStatus(AffairStatus status);
}
