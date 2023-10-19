package com.example.javatest.exceptions;

public class CarDuplicateException extends RuntimeException {

    public CarDuplicateException(String chassis){
        super("Chassis " + chassis + " is duplicated.");
    }

}
