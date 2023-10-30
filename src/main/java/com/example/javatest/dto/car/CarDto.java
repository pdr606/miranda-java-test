package com.example.javatest.dto.car;

import com.example.javatest.config.validations.CreateCarValidation;
import com.example.javatest.model.Car;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@Builder
public record CarDto(

        Long id,
        @NotEmpty(message = "Vehicle is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String vehicle,
        @NotEmpty(message = "Brand is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String brand,
        @NotNull(message = "Year is required", groups = CreateCarValidation.class) @PastOrPresent(message = "The year must be equal to or less than the current year", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Year year,
        @NotEmpty(message = "Description is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String description,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        boolean sold,
        @NotEmpty(message = "Chassis is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String chassis,
        @NotNull(message = "Price is required ", groups = CreateCarValidation.class)
        @PositiveOrZero(message = "Price must be greater than zero", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        BigDecimal price,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime created,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime updated

        ) {

    public static Car toEntity(CarDto dto){
        return Car.builder().vehicle(dto.vehicle()).brand(dto.brand())
                .year(dto.year()).description(dto.description())
                .chassis(dto.chassis()).price(dto.price()).build();
    }

    public static CarDto toDto(Car entity){
        return CarDto.builder().id(entity.getId()).vehicle(entity.getVehicle()).brand(entity.getBrand())
                .year(entity.getYear()).description(entity.getDescription())
                .chassis(entity.getChassis()).price(entity.getPrice())
                .created(entity.getCreated()).updated(entity.getCreated())
                .build();
    }
}
