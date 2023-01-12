package com.solvd.course.lawoffice.domain.consultation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private String field;
    private String fieldMessage;

    public ValidationException(String message, String field, String fieldMessage) {
        super(message);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }
}
