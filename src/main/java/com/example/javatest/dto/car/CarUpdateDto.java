package com.example.javatest.dto.car;


import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CarUpdateDto(
        String description,
        boolean sold,
        @PositiveOrZero(message = "Price must be greater than zero")
        BigDecimal price
) {
}
