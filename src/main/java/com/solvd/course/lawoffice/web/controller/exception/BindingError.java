package com.solvd.course.lawoffice.web.controller.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Validation error information")
public class BindingError {

    @Schema(description = "Field where validation error occurred")
    private String field;

    @Schema(description = "Validation error message")
    private String message;

}
