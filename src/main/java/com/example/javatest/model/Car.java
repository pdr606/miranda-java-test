package com.example.javatest.model;

import com.example.javatest.dto.CarCreateDto;
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
    @Column(name = "FABRICATION_YEAR")
    private Integer year;
    private String description;
    private Boolean sold;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String chassis;
    private BigDecimal price;

    public Car(CarCreateDto data) {
        this.brand = data.brand();
        this.year = data.year();
        this.description = data.description();
        this.sold = data.sold();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.chassis = data.chassis();
        this.price = data.price();
    }
}
