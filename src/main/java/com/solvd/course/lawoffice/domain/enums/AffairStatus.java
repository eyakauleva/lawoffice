package com.solvd.course.lawoffice.domain.enums;

import java.util.stream.Stream;

public enum AffairStatus {
    IN_PROGRESS("IN_PROGRESS"),
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");
    private final String value;

    AffairStatus(String value) {
        this.value = value;
    }

    public static AffairStatus getEnumByValue(String value) {
        return Stream.of(AffairStatus.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return value;
    }
}
