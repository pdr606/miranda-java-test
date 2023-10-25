package com.example.javatest.service.car;

import com.example.javatest.dto.car.CarUpdateDto;
import com.example.javatest.dto.car.CarCreateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;

    @Override
    public Page<Car> getAllCarsPageable(Pageable pageable) {
        if (pageable.getPageSize() > 10) {
            pageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
        }
        return carRepository.findAll(pageable);
    }

    @Override
    public List<Car> getAllByParams(String vehicle, String brand, BigDecimal price) {
        Car car = new Car();
        car.setVehicle(vehicle);
        car.setBrand(brand);
        car.setPrice(price);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Car> example = Example.of(car, matcher);
        return carRepository.findAll(example);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(()
        -> new CarNotFoundException(id));
    }

    @Override
    public void saveCar(@Valid CarCreateDto data) {
        try{
            Car car = CarCreateDto.toEntity(data);
            carRepository.save(car);
        } catch (DataIntegrityViolationException ex){
            throw new CarDuplicateException(data.chassis());
        }
    }

    @Override
    public Car updateCar(Long id, CarUpdateDto data) {
            Car entity = getCarById(id);
            updateDataCar(entity, data);
            return carRepository.save(entity);
    }

    @Override
    public void deleteCar(Long id) {
        if(getCarById(id) != null){
            carRepository.deleteById(id);
            return;
        }
        throw new CarNotFoundException(id);
    }

    @Override
    public void updateDataCar(Car entity, CarUpdateDto data) {
        if(data.description() != null){
            entity.setDescription(data.description());
        }
        if(data.price() != null){
            entity.setPrice(data.price());
        }
        entity.setSold(data.sold());
    }
}
