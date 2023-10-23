package com.example.javatest.service;

import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class CarImp implements CarGetaway {

    private final CarRepository carRepository;

    @Override
    @Nullable
    public List<Car> getByParams(String vehicle, String brand, BigDecimal price) {
        return carRepository.findCarsByVehicleAndBrandAndPrice(vehicle, brand, price);
    }

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
        try{
            carRepository.save(new Car(data));
        } catch (DataIntegrityViolationException ex){
            throw new CarDuplicateException(data.chassis());
        }
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
