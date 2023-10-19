package com.example.javatest.controller;

import java.math.BigDecimal;

public record CarUpdateDto(
        String description,
        boolean sold,
        BigDecimal price
) {
}
