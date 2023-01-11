package com.solvd.course.lawoffice.domain.consultation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsultationUniqueConstraint {

    LAWYER_BUSY(1),
    USER_BUSY(2);

    private final Integer value;

}
