package com.example.javatest.model;

import com.example.javatest.dto.CarCreateDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vehicle", nullable = false)
    private String vehicle;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "fabrication_year", nullable = false)
    private Integer year;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sold", insertable = true)
    private boolean sold;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updated;

    @Column(name = "chassis", unique = true, nullable = false)
    private String chassis;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Car(CarCreateDto data) {
        this.vehicle = data.vehicle();
        this.brand = data.brand();
        this.year = data.year();
        this.description = data.description();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.chassis = data.chassis();
        this.price = data.price();
        this.sold = true;
    }
    @PreUpdate
    private void setUpdated(){
        this.updated = LocalDateTime.now();
    }
}
