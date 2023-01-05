package com.solvd.course.lawoffice.domain.enums;

import java.util.stream.Stream;

public enum Role {
    ADMIN("ADMIN"),
    CLIENT("CLIENT"),
    LAWYER("LAWYER");
    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static Role getEnumByValue(String value) {
        return Stream.of(Role.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return value;
    }
}
