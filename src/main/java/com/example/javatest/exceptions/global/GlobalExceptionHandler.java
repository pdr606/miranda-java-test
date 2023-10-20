package com.example.javatest.exceptions.global;


import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CarDuplicateException.class)
    public StandardError carDuplicateException(CarDuplicateException ex, HttpServletRequest request){
        String error = "Car duplicate";
        HttpStatus status = HttpStatus.CONFLICT;
        return new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoundException.class)
    public StandardError carNotFoundException(CarNotFoundException ex, HttpServletRequest request){
        String error = "Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
    }
}
