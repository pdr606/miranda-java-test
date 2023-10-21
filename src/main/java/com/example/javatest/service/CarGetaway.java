package com.example.javatest.service;

import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarGetaway {

    Page<Car> getAllCars(Pageable pageable);
    Car getCarById(Long id);
    void registerCar(CarCreateDto data);
    boolean checkIfCarExist(String chassis);
    Car updateCar(Long id, CarUpdateDto data);
    void deleteCar(Long id);
    void updateData(Car entity, CarUpdateDto data);
}
