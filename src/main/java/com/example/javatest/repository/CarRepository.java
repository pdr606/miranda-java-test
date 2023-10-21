package com.example.javatest.repository;

import com.example.javatest.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByVehicleAndBrandAndPrice(String vehicle, String brand, BigDecimal price);
    
}
