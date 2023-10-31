package com.example.javatest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.Year;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TB_CAR")
public class CarEntity {

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

    @Column(name = "chassis", unique = true, nullable = false)
    private String chassis;

    @Embedded
    private CreateAndUpdateEntity dateTime;

    @Embedded
    private PriceSalesEntity salesInfo;

    @PrePersist
    private void initializeCreateAndUpdate() {
        this.dateTime = new CreateAndUpdateEntity();
    }

}
