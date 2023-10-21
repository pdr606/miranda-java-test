package com.example.javatest.exceptions.global;


import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CarDuplicateException.class)
    public StandardError carDuplicateException(CarDuplicateException ex, HttpServletRequest request){
        String error = "Car duplicate";
        HttpStatus status = HttpStatus.CONFLICT;
        return new StandardError(Instant.now(), status.value(), Collections.singletonList(error), ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoundException.class)
    public StandardError carNotFoundException(CarNotFoundException ex, HttpServletRequest request){
        String error = "Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new StandardError(Instant.now(), status.value(), Collections.singletonList(error), ex.getMessage(), request.getRequestURI());
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StandardError handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> messages.add(err.getDefaultMessage()));
        String message = "Invalid data";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new StandardError(Instant.now(), status.value(), messages, message, request.getRequestURI());
    }
    
}
