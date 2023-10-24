package com.example.javatest.service.car;

import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;
import com.example.javatest.service.generic.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CarService extends CrudService<Car, CarCreateDto, CarUpdateDto> {

    Page<Car> getAllCarsPageable(Pageable pageable);
    List<Car> getAllByParams(String vehicle, String brand, BigDecimal price);
}
