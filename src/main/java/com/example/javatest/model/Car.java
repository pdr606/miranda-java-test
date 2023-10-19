package com.example.javatest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private Integer year;
    private String description;
    private boolean sold;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String chassis;
    private BigDecimal price;
}
