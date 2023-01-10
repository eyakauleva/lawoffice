package com.solvd.course.lawoffice.web.controller.exception;

import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
            validationErrors.add(new ValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));
        return new ExceptionBody("Validation errors", validationErrors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentTypeMismatchException() {
        return new ExceptionBody("Bad parameters");
    }

    @ExceptionHandler({UniqueConstraintViolationException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleOtherBadRequestExceptions(Exception ex) {
        return new ExceptionBody(ex.getMessage());
    }

//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ExceptionBody handleAllOtherExceptions() {
//        return new ExceptionBody("Internal server error");
//    }
}
