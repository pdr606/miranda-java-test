package com.example.javatest.service;

import com.example.javatest.controller.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService implements CarGetaway {

    private final CarRepository carRepository;
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(()
        -> new CarNotFoundException(id));
    }


    @Override
    public void registerCar(CarCreateDto data) {
        if(!checkIfCarExist(data.chassis())){
            var car = new Car(data);
            carRepository.save(car);
        }
        throw new CarDuplicateException(data.chassis());
    }

    @Override
    public boolean checkIfCarExist(String chassis) {
        return carRepository.existsByChassis(chassis);
    }

    @Override
    public void updateCar(Long id, CarUpdateDto data) {
            Car entity = getCarById(id);
            updateData(entity, data);
            carRepository.save(entity);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateData(Car entity, CarUpdateDto data) {
        entity.setDescription(data.description());
        entity.setSold(data.sold());
        entity.setPrice(data.price());
    }
}
