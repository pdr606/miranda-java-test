package com.example.javatest.mapper;

import com.example.javatest.dto.car.CarDto;
import com.example.javatest.model.CarEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    void updateCarFromDto(CarDto dto, @MappingTarget CarEntity carEntity);

    @Mapping(source = "id", target = "id")
    List<CarDto> toDtoList(List<CarEntity> list);
}

