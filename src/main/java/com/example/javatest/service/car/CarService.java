package com.example.javatest.service.car;

import com.example.javatest.dto.car.CarCreateDto;
import com.example.javatest.dto.car.CarUpdateDto;
import com.example.javatest.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    void saveCar(CarCreateDto data);
    void deleteCar(Long id);
    void updateDataCar(Car entity, CarUpdateDto data);
    Car getCarById(Long id);
    Car updateCar(Long id, CarUpdateDto data);
    Page<Car> getAllCarsPageable(Pageable pageable);
    List<Car> getAllByParams(String vehicle, String brand, BigDecimal price);

}
