package com.example.javatest.model;

import com.example.javatest.dto.CarCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String brand;
    @NotNull
    @Column(name = "FABRICATION_YEAR")
    private Integer year;
    @NotEmpty
    private String description;
    @NotNull
    private Boolean sold;
    private LocalDateTime created;
    private LocalDateTime updated;
    @NotEmpty
    private String chassis;
    @NotNull
    private BigDecimal price;

    public Car(CarCreateDto data) {
        this.brand = data.brand();
        this.year = data.year();
        this.description = data.description();
        this.sold = true;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.chassis = data.chassis();
        this.price = data.price();
    }
    @PreUpdate
    private void setUpdated(){
        this.updated = LocalDateTime.now();
    }
}
