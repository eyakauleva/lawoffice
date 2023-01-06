package com.solvd.course.lawoffice.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    //TODO catch org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
    // (thrown when in request params sent not existing enum)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder message = new StringBuilder("Validation errors: \n");
        for (FieldError fieldError : result.getFieldErrors())
            message.append(fieldError.getDefaultMessage()).append("\n");
        return new ResponseEntity<>(new ExceptionBody(message.toString()), HttpStatus.BAD_REQUEST);
    }
}
