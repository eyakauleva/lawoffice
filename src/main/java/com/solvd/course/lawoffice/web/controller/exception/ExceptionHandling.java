package com.solvd.course.lawoffice.web.controller.exception;

import com.solvd.course.lawoffice.persistence.exception.UniqueConstraintViolationException;
import com.solvd.course.lawoffice.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder message = new StringBuilder("Validation errors: \n");
        for (FieldError fieldError : result.getFieldErrors())
            message.append(fieldError.getDefaultMessage()).append("\n");
        return new ResponseEntity<>(new ExceptionBody(message.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class, UniqueConstraintViolationException.class})
    public ResponseEntity<ExceptionBody> handleCustomExceptions(Exception ex) {
        return new ResponseEntity<>(new ExceptionBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>(new ExceptionBody("Bad parameters"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionBody> handleOtherExceptions() {
        return new ResponseEntity<>(new ExceptionBody("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
