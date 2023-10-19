package com.example.javatest.service;

import com.example.javatest.controller.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
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
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }


    @Override
    public void createCar(CarCreateDto data) {
        if(!checkIfCarExist(data.chassis())){
            var car = new Car(data);
            carRepository.save(car);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean checkIfCarExist(String chassis) {
        return carRepository.existsByChassis(chassis);
    }

    @Override
    public void updateCar(Long id, CarUpdateDto data) {
        try{
            Optional<Car> entity = getCarById(id);
            updateData(entity, data);
            carRepository.save(entity.get());
        } catch (EntityNotFoundException ex){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateData(Optional<Car> entity, CarUpdateDto data) {
        entity.get().setDescription(data.description());
        entity.get().setSold(data.sold());
        entity.get().setPrice(data.price());
    }
}
