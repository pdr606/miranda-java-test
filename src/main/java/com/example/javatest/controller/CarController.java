package com.example.javatest.controller;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;
import com.example.javatest.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    @PostMapping
    public void updateCar(@PathVariable Long id, @RequestBody CarUpdateDto data){
        carService.updateCar(id, data);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Car findCarById(@PathVariable Long id){
        return carService.getCarById(id);
            }

}
