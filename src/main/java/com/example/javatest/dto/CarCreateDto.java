package com.example.javatest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public record CarCreateDto(
                            @jakarta.validation.constraints.NotNull(message = "Vehicle is required")
                            String vehicle,
                            @jakarta.validation.constraints.NotEmpty(message = "Brand is required")
                            String brand,
                           @NotNull(message = "Year is required")
                           Integer year,
                           @jakarta.validation.constraints.NotEmpty(message = "Description is required")
                           String description,
                           @NotEmpty(message = "Chassis is required")
                           String chassis,
                           @NotNull(message = "Price is required ")
                           @Range(min = 0, message = "Price must be greater than zero")
                           BigDecimal price) {
}
