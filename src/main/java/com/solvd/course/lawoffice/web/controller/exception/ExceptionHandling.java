package com.solvd.course.lawoffice.web.controller.exception;

import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBindingExceptions(BindException ex) {
        List<BindingError> bindingErrors = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        bindingErrors.add(
                                new BindingError(
                                        fieldError.getObjectName() + "." + fieldError.getField(),
                                        fieldError.getDefaultMessage())));
        return new ExceptionBody("Binding errors", bindingErrors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleUniqueConstraintViolationException(UniqueConstraintViolationException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionBody handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    //TODO uncomment when everything is done
//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ExceptionBody handleAllOtherExceptions() {
//        return new ExceptionBody("Internal server error");
//    }

}
