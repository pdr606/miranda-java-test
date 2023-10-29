package com.example.javatest.mapper;

import com.example.javatest.dto.car.CarDto;
import com.example.javatest.model.Car;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "id", source = "id")
    List<CarDto> toDtoList(List<Car> list);
}
