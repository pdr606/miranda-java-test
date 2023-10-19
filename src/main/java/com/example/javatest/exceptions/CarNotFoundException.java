package com.example.javatest.exceptions;

public class CarNotFoundException extends RuntimeException {

    public  CarNotFoundException(Long id){
        super("Car of id: " + id + " don't exist");
    }
}
