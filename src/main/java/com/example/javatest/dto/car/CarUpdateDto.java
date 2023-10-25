package com.example.javatest.dto.car;


import java.math.BigDecimal;

public record CarUpdateDto(
        String description,
        boolean sold,
        BigDecimal price
) {
}
