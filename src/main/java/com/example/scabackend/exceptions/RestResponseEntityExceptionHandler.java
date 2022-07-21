package com.example.scabackend.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccountException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> handleUserNotAllowedException(final Exception ex, final WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        String[] parts = ex.getMessage().split("-");
        errorMessage.setMessage(parts[1]);
        errorMessage.setTitle(parts[0]);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
//        return new ResponseEntity<>(errorMessage,status );
//    }
}
