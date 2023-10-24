package com.example.javatest.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Year;

public record CarCreateDto(
                            @NotNull(message = "Vehicle is required")
                            String vehicle,
                            @NotEmpty(message = "Brand is required")
                            String brand,
                           @NotNull(message = "Year is required")
                            @PastOrPresent(message = "The year must be equal to or less than the current year")
                            Year year,
                           @NotEmpty(message = "Description is required")
                           String description,
                           @NotEmpty(message = "Chassis is required")
                           String chassis,
                           @NotNull(message = "Price is required ")
                           @PositiveOrZero(message = "Price must be greater than zero")
                           BigDecimal price) {
}
