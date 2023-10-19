package com.example.javatest.dto;

import java.math.BigDecimal;

public record CarUpdateDto(
        String description,
        boolean sold,
        BigDecimal price
) {
}
