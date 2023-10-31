package com.example.javatest.car;


import com.example.javatest.dto.car.CarDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.repository.CarRepository;
import com.example.javatest.service.car.CarServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
 class CarEntityServiceTest {

    @Autowired
   private CarServiceImp carService;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    void setup() throws CarDuplicateException {

        CarDto carDto = CarDto.builder().vehicle("Corsa")
                        .brand("CHEVROLET").year(Year.of(2009))
                        .description("Good Car").chassis("83HN38FHF38HF3")
                        .price(new BigDecimal("220000")).build();

        carService.save(carDto);
    }


    @Test
    @DisplayName("Test if car create with success")
    void saveCar_WithValidData_ReturnsCarDto() throws CarNotFoundException, CarDuplicateException {

        CarDto carDto = CarDto.builder().vehicle("Corsa")
                .brand("CHEVROLET").year(Year.of(2009))
                .description("Good Car").chassis("83HN38FHF38HF3")
                .price(new BigDecimal("220000")).build();

        carService.save(carDto);

        CarDto car = carService.getById(1L);

        assertThat(car.id()).isNotNull();
        assertThat(car.vehicle()).isEqualTo("Corsa");
        assertThat(car.brand()).isEqualTo("CHEVROLET");
        assertThat(car.year()).isEqualTo(Year.of(2009));
        assertThat(car.description()).isEqualTo("Good car");
        assertThat(car.chassis()).isEqualTo("83HN38FHF38HF3");

    }

    @Test
    @DisplayName("Test if update car with success")
    void updateCar_WithValidData_ReturnsCar() throws CarNotFoundException {

        CarDto carDto = CarDto.builder().vehicle("Corsa")
                .brand("CHEVROLET").year(Year.of(2009))
                .description("Good Car").chassis("83HN38FHF38HF3")
                .price(new BigDecimal("220000")).build();

        carService.save(carDto);

        CarDto carEntity = carService.update(1L, carDto);

        assertThat(carEntity.description()).isEqualTo(carDto.description());
    }
}
