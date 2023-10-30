package com.example.javatest.service.car;

import com.example.javatest.dto.car.CarDto;
import com.example.javatest.service.CrudService;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CarService extends CrudService<CarDto> {
    List<CarDto> getAllCarsPageable(Pageable pageable);
    List<CarDto> getAllByParams(String vehicle, String brand, BigDecimal price);

}
