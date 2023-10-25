package com.example.javatest.dto.car;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

public record CarResponseDto(
        Long id,
        String brand,
        Year year,
        String description,
        boolean sold,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal price
) {
}
