package com.example.javatest.service;

import com.example.javatest.dto.CarResponseDto;
import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService implements CarGetaway {

    private final CarRepository carRepository;
    @Override
    public Page<Car> getAllCars(Pageable pageable) {
        if (pageable.getPageSize() > 10) {
            pageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
        }
        return carRepository.findAll(pageable);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(()
        -> new CarNotFoundException(id));
    }

    @Override
    public void registerCar(@Valid CarCreateDto data) {
        if(checkIfCarExist(data.chassis())){
            throw  new CarDuplicateException(data.chassis());
        }
        carRepository.save(new Car(data));
    }

    @Override
    public boolean checkIfCarExist(String chassis) {
        return carRepository.existsByChassis(chassis);
    }

    @Override
    public Car updateCar(Long id, CarUpdateDto data) {
            Car entity = getCarById(id);
            updateData(entity, data);
            return carRepository.save(entity);
    }

    @Override
    public void deleteCar(Long id) {
        if(getCarById(id) != null){
            carRepository.deleteById(id);
        }
    }

    @Override
    public void updateData(Car entity, CarUpdateDto data) {
        entity.setDescription(data.description());
        entity.setSold(data.sold());
        entity.setPrice(data.price());
    }
}
