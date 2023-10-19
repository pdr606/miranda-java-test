package com.example.javatest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarResponseDto(
        Long id,
        String brand,
        Integer year,
        String description,
        Boolean sold,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal price
) {
}
