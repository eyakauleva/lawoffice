package com.solvd.course.lawoffice.web.controller.exception;

import com.solvd.course.lawoffice.domain.consultation.ValidationException;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBindingExceptions(BindException ex) {
        List<BindingError> bindingErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        new BindingError(
                                fieldError.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ExceptionBody("Binding errors", bindingErrors);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleValidationException(ValidationException ex) {
        BindingError bindingError = new BindingError(ex.getField(), ex.getFieldMessage());
        return new ExceptionBody(ex.getMessage(), Collections.singletonList(bindingError));
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFoundException(ResourceDoesNotExistException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler({UniqueConstraintViolationException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBadRequestsExceptions(Exception ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionBody handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ExceptionBody(ex.getMessage());
    }

//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ExceptionBody handleAllOtherExceptions() {
//        return new ExceptionBody("Internal server error");
//    }

}
