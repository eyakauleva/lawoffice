package com.solvd.course.lawoffice.web.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {
    private String objectName;
    private String fieldName;
    private String message;
}
