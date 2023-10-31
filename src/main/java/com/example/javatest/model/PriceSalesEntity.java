package com.example.javatest.model;


import com.example.javatest.config.validations.CreateCarValidation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@lombok.Data
@NoArgsConstructor
@Builder
public class PriceSalesEntity {

    @NotNull(message = "Price is required ", groups = CreateCarValidation.class)
    @PositiveOrZero(message = "Price must be greater than zero", groups = CreateCarValidation.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "sold", insertable = true)
    @Value("false")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean sold;
}
