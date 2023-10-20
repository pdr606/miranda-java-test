package com.example.javatest.mapper;

import com.example.javatest.dto.CarResponseDto;
import com.example.javatest.model.Car;

public class CarMapper {
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
}
