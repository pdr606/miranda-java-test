package com.example.javatest.service.car;

import com.example.javatest.dto.car.CarDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.mapper.CarMapper;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarDto> getAllCarsPageable(Pageable pageable) {
        if (pageable.getPageSize() > 10) {
            pageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
        }
        return carMapper.INSTANCE.toDtoList(carRepository.findAll(pageable).getContent());
    }

    @Override
    public List<CarDto> getAllByParams(String vehicle, String brand, BigDecimal price) {
        Car car = new Car();
        car.setVehicle(vehicle);
        car.setBrand(brand);
        car.setPrice(price);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Car> example = Example.of(car, matcher);
        return carMapper.INSTANCE.toDtoList(carRepository.findAll(example));
    }

    @Override
    public CarDto getById(Long id) {
        return carRepository.findById(id).map(CarDto::toDto) .orElseThrow(()
        -> new CarNotFoundException(id));
    }

    @Override
    public void save(@Valid CarDto dto) {
        try{
            Car car = CarDto.toEntity(dto);
            carRepository.save(car);
        } catch (DataIntegrityViolationException ex){
            throw new CarDuplicateException(dto.chassis());
        }
    }

    @Override
    public CarDto update(Long id, CarDto dto) {
            try{
                Car entity = carRepository.getReferenceById(id);

                if(entity.getChassis().equals(dto.chassis())){
                    throw new CarDuplicateException(dto.chassis());
                }

                carMapper.updateCarFromDto(dto, entity);

                return CarDto.toDto(carRepository.save(entity));
            } catch (EntityNotFoundException ex){
                throw new CarNotFoundException(id);
            }
    }

    @Override
    public void delete(Long id) {
        if(getById(id) != null){
            carRepository.deleteById(id);
            return;
        }
        throw new CarNotFoundException(id);
    }
}
