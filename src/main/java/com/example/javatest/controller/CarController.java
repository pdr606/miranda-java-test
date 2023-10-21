package com.example.javatest.controller;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.dto.CarResponseDto;
import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.mapper.CarMapper;
import com.example.javatest.model.Car;
import com.example.javatest.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    @GetMapping
    @Cacheable("cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> findAllCars(@PageableDefault(
                                direction = Sort.Direction.ASC,
                                page = 0,
                                size = 10
                                )@Valid Pageable pageable){
        return carService.getAllCars(pageable).getContent();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void registerCar(@Valid @RequestBody CarCreateDto data){
         carService.registerCar(data);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public CarResponseDto updateCar(@PathVariable Long id, @RequestBody CarUpdateDto data){
        return CarMapper.toResponse(carService.updateCar(id, data));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CarResponseDto findCarById(@PathVariable Long id){
        return CarMapper.toResponse(carService.getCarById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }
}
