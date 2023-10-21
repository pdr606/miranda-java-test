package com.example.javatest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarResponseDto(
        Long id,
        String brand,
        Integer year,
        String description,
        boolean sold,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal price
) {
}
