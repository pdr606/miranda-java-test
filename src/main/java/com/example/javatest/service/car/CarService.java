package com.example.javatest.service.car;

import com.example.javatest.dto.car.CarDto;
import com.example.javatest.model.Car;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    void saveCar(CarDto dto);
    void deleteCar(Long id);
    void updateDataCar(Car entity, CarDto dto);
    CarDto getCarById(Long id);
    CarDto updateCar(Long id, CarDto dto);
    List<CarDto> getAllCarsPageable(Pageable pageable);
    List<CarDto> getAllByParams(String vehicle, String brand, BigDecimal price);

}
