package com.example.javatest.controller;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.dto.CarResponseDto;
import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.mapper.CarMapper;
import com.example.javatest.model.Car;
import com.example.javatest.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void registerCar(@RequestBody @Valid CarCreateDto data){
         carService.registerCar(data);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/update/{id}")
    public CarResponseDto updateCar(@PathVariable Long id, @RequestBody CarUpdateDto data){
        return CarMapper.toResponse(carService.updateCar(id, data));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CarResponseDto findCarById(@PathVariable Long id){
        return CarMapper.toResponse(carService.getCarById(id));
    }

}
