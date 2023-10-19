package com.example.javatest.controller;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.model.Car;
import com.example.javatest.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody CarCreateDto data){
         carService.create(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<Car> findById(@PathVariable Long id){
        return carService.findById(id);
            }

}
