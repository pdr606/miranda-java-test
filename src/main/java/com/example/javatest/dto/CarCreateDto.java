package com.example.javatest.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record CarCreateDto(
                            @NotNull(message = "Vehicle is required")
                            String vehicle,
                            @NotEmpty(message = "Brand is required")
                            String brand,
                           @NotNull(message = "Year is required")
                           Integer year,
                           @NotEmpty(message = "Description is required")
                           String description,
                           @NotEmpty(message = "Chassis is required")
                           String chassis,
                           @Range(min = 0, message = "Price must be greater than zero")
                           BigDecimal price) {
}
