package com.example.javatest.exceptions.global;


import com.example.javatest.exceptions.CarDuplicateException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CarDuplicateException.class)
    public StandardError carDuplicateException(CarDuplicateException ex, HttpServletRequest request){
        String error = "Car duplicate";
        HttpStatus status = HttpStatus.CONFLICT;
        return new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
    }
}
