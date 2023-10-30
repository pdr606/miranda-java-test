package com.example.javatest.controller;

import com.example.javatest.config.validations.CreateCarValidation;
import com.example.javatest.dto.car.CarDto;
import com.example.javatest.service.car.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> findAll(
            @PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 10)
            @Validated(CreateCarValidation.class) Pageable pageable){
        return this.carService.getAllCarsPageable(pageable);
    }

    @GetMapping(value = "/query")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> findByParams(
            @RequestParam(value = "vehicle", required = false) String vehicle,
            @RequestParam(value = "brand", required = false )String brand,
            @RequestParam(value = "price", required = false)BigDecimal price) {
        return this.carService.getAllByParams(vehicle, brand, price);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save( @RequestBody @Validated(value = CreateCarValidation.class) CarDto dto){
         this.carService.save(dto);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public CarDto update(@PathVariable Long id, @RequestBody @Validated CarDto dto){
        return this.carService.update(id, dto);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CarDto findById(@PathVariable Long id){
        return this.carService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        this.carService.delete(id);
    }
}
