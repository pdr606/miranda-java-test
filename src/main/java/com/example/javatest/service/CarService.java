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
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }


    @Override
    public void create(CarCreateDto data) {
        if(!carExist(data.chassis())){
            var car = new Car(data);
            carRepository.save(car);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean carExist(String chassis) {
        return carRepository.existsByChassis(chassis);
    }

    @Override
    public void update(Long id, CarUpdateDto data) {
        try{
            Optional<Car> entity = findById(id);
            updateData(entity, data);
            carRepository.save(entity.get());
        } catch (EntityNotFoundException ex){
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);

    }

    @Override
    public void updateData(Optional<Car> entity, CarUpdateDto data) {
        entity.get().setDescription(data.description());
        entity.get().setSold(data.sold());
        entity.get().setPrice(data.price());

    }
}
