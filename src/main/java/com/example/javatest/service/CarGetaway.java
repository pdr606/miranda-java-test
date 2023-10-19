package com.example.javatest.service;

import com.example.javatest.controller.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarGetaway {

    List<Car> findAll();
    Optional<Car> findById(Long id);
    void create(CarCreateDto data);
    boolean carExist(String chassis);
    void update(Long id, CarUpdateDto data);
    void delete(Long id);
    void updateData(Optional<Car> entity, CarUpdateDto data);
}
