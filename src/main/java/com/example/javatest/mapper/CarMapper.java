package com.example.javatest.mapper;

import com.example.javatest.dto.car.CarResponseDto;
import com.example.javatest.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public final class CarMapper {
     private CarMapper(){
     }
     public static CarResponseDto toResponse(Car data){
        return new CarResponseDto(
                data.getId(),
                data.getBrand(),
                data.getYear(),
                data.getDescription(),
                data.isSold(),
                data.getCreated(),
                data.getUpdated(),
                data.getPrice()
        );
    }

    public static List<CarResponseDto> toResponse(List<Car> data){
         return data.stream().map(CarMapper::toResponse
                 ).toList();
    }
}
