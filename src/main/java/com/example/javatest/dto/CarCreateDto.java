package com.example.javatest.dto;

import java.math.BigDecimal;

public record CarCreateDto(
                            String vehicle,
                            String brand,
                           Integer year,
                           String description,
                           String chassis,
                           BigDecimal price) {
}
