package com.example.javatest.dto.car;

import com.example.javatest.model.Car;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Year;

@Builder
public record CarCreateDto(
                            @NotNull(message = "Vehicle is required")
                            String vehicle,
                            @NotEmpty(message = "Brand is required")
                            String brand,
                           @NotNull(message = "Year is required") @PastOrPresent(message = "The year must be equal to or less than the current year")
                            Year year,
                           @NotEmpty(message = "Description is required")
                           String description,
                           @NotEmpty(message = "Chassis is required")
                           String chassis,
                           @NotNull(message = "Price is required ")
                           @PositiveOrZero(message = "Price must be greater than zero")
                           BigDecimal price) {

    public static Car toEntity(CarCreateDto dto){
        return Car.builder().vehicle(dto.vehicle()).brand(dto.brand())
                .year(dto.year()).description(dto.description())
                .chassis(dto.chassis()).price(dto.price()).build();
    }
}

