package com.solvd.course.lawoffice.domain.enums;

public enum ConsultationUniqueConstraint {
    LAWYER_BUSY(1),
    USER_BUSY(2);
    private final Integer value;

    ConsultationUniqueConstraint(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
