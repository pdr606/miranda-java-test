package com.example.javatest.dto;

import java.math.BigDecimal;

public record CarCreateDto(String brand,
                           Integer year,
                           String description,
                           Boolean sold,
                           String chassis,
                           BigDecimal price) {
}
