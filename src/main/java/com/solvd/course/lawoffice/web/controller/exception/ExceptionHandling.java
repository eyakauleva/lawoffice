package com.solvd.course.lawoffice.web.controller.exception;

import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder message = new StringBuilder("Validation errors: ");
        for (FieldError fieldError : result.getFieldErrors())
            message.append(fieldError.getDefaultMessage());
        return new ResponseEntity<>(new ExceptionBody(message.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>(new ExceptionBody("Bad parameters"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UniqueConstraintViolationException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ExceptionBody> handleOtherBadRequestExceptions(Exception ex) {
        return new ResponseEntity<>(new ExceptionBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<ExceptionBody> handleAllOtherExceptions() {
//        return new ResponseEntity<>(new ExceptionBody("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
