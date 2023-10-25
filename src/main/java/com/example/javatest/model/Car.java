package com.example.javatest.model;

import com.example.javatest.dto.car.CarCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
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
    private Year year;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sold", insertable = true)
    @Value("false")
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

    @PreUpdate
    private void setUpdated(){
        this.updated = LocalDateTime.now();
    }
    @PrePersist
    private void setPersist(){
        this.created = LocalDateTime.now();
    }

    public Car(CarCreateDto data) {
        this.vehicle = data.vehicle();
        this.brand = data.brand();
        this.year = data.year();
        this.description = data.description();
        this.chassis = data.chassis();
        this.price = data.price();
    }
}
