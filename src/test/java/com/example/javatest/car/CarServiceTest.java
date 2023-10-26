package com.example.javatest.car;


import com.example.javatest.dto.car.CarCreateDto;
import com.example.javatest.dto.car.CarUpdateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import com.example.javatest.service.car.CarService;
import com.example.javatest.service.car.CarServiceImp;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
 class CarServiceTest {

    @Autowired
   private CarServiceImp carService;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    void setup() throws CarDuplicateException {
        CarCreateDto carDto = new CarCreateDto(
                "Corsa",
                "CHEVROLET",
                Year.of(2009),
                "Good car",
                "83HN38FHF38HF3",
                new BigDecimal("290000")
        );

        carService.saveCar(carDto);
    }



    @Test
    @DisplayName("Test if car create with success")
    void saveCar_WithValidData_ReturnsCarDto() throws CarNotFoundException, CarDuplicateException {

        CarCreateDto carDto = new CarCreateDto(
                "Corsa",
                "CHEVROLET",
                Year.of(2009),
                "Good car",
                "83HN38FHF38HF3",
                new BigDecimal("290000")
        );

        carService.saveCar(carDto);

        Car car = carService.getCarById(1L);

        assertThat(car.getId()).isNotNull();
        assertThat(car.getVehicle()).isEqualTo("Corsa");
        assertThat(car.getBrand()).isEqualTo("CHEVROLET");
        assertThat(car.getYear()).isEqualTo(Year.of(2009));
        assertThat(car.getDescription()).isEqualTo("Good car");
        assertThat(car.getChassis()).isEqualTo("83HN38FHF38HF3");

    }

    @Test
    @DisplayName("Test if update car with success")
    void updateCar_WithValidData_ReturnsCar() throws CarNotFoundException {

        CarUpdateDto carDto = new CarUpdateDto(
                "Best Car",
                true,
                null
        );


        Car carEntity = carService.updateCar(1L, carDto);

        assertThat(carEntity.getDescription()).isEqualTo(carDto.description());
    }
}
