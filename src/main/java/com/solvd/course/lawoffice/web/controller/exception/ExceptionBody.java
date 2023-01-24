package com.solvd.course.lawoffice.web.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "Model to return in case of exception")
public class ExceptionBody {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "Exception message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "List of validation errors")
    private List<BindingError> bindingErrors;

    public ExceptionBody(String message) {
        this.message = message;
    }

}
