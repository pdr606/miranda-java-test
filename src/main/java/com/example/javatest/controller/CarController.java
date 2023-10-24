package com.example.javatest.controller;

import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.dto.CarResponseDto;
import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.mapper.CarMapper;
import com.example.javatest.model.Car;
import com.example.javatest.service.car.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    @GetMapping
    @Cacheable("cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> findAllCars(@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 10)@Valid Pageable pageable){
        return carService.getAllCarsPageable(pageable).getContent();
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> findCarByParams(@RequestParam(value = "vehicle", required = false) String vehicle,
                                     @RequestParam(value = "brand", required = false )String brand,
                                     @RequestParam(value = "price", required = false)BigDecimal price) {
        return carService.getAllByParams(vehicle, brand, price);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void registerCar(@Valid @RequestBody CarCreateDto data){
         carService.save(data);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public CarResponseDto updateCar(@PathVariable Long id, @RequestBody CarUpdateDto data){
        return CarMapper.toResponse(carService.update(id, data));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CarResponseDto findCarById(@PathVariable Long id){
        return CarMapper.toResponse(carService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.delete(id);
    }
}
