package com.example.javatest.dto.car;

import com.example.javatest.config.validations.CreateCarValidation;
import com.example.javatest.model.CarEntity;
import com.example.javatest.model.CreateAndUpdateEntity;
import com.example.javatest.model.PriceSalesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
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

        @NotNull(message = "Year is required", groups = CreateCarValidation.class)
        @PastOrPresent(message = "The year must be equal to or less than the current year", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Year year,

        @NotEmpty(message = "Description is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String description,

        @NotEmpty(message = "Chassis is required", groups = CreateCarValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String chassis,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        CreateAndUpdateEntity dateTime,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Valid
        @NotNull(message = "SalesInfo is required", groups = CreateCarValidation.class)
        PriceSalesEntity salesInfo

        ) {

    public static CarEntity toEntity(CarDto dto){
        return CarEntity.builder()
                .vehicle(dto.vehicle())
                .brand(dto.brand())
                .year(dto.year())
                .description(dto.description())
                .chassis(dto.chassis())
                .salesInfo(PriceSalesEntity.builder().price(dto.salesInfo.getPrice()).build())
                .build();
    }

    public static CarDto toDto(CarEntity entity){
        return CarDto.builder()
                .id(entity.getId())
                .vehicle(entity.getVehicle())
                .brand(entity.getBrand())
                .year(entity.getYear())
                .description(entity.getDescription())
                .chassis(entity.getChassis())
                .salesInfo(PriceSalesEntity.builder().price(entity.getSalesInfo().getPrice()).build())
                .dateTime(CreateAndUpdateEntity.builder().created(entity.getDateTime().getCreated()).updated(entity.getDateTime().getUpdated()).build()).build();
    }
}

