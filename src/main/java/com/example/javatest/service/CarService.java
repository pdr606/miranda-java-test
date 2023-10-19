package com.example.javatest.service;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService implements CarGetaway {

    private final CarRepository carRepository;
    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }


    @Override
    public void create(CarCreateDto data) {
        Car car = new Car(data);
        carRepository.save(car);
    }

    @Override
    public void update(Car data) {

    }

    @Override
    public void delete(Long id) {

    }
}
