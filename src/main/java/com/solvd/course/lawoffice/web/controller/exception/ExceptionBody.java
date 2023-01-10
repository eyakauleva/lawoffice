package com.solvd.course.lawoffice.web.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionBody {
    private String message;
    private List<ValidationError> validationErrors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}
