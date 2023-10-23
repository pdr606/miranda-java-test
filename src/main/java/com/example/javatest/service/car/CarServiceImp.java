package com.example.javatest.service.car;

import com.example.javatest.dto.CarUpdateDto;
import com.example.javatest.dto.CarCreateDto;
import com.example.javatest.exceptions.CarDuplicateException;
import com.example.javatest.exceptions.CarNotFoundException;
import com.example.javatest.model.Car;
import com.example.javatest.repository.CarRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;

    @Override
    @Nullable
    public List<Car> getByParams(String vehicle, String brand, BigDecimal price) {
        return carRepository.findCarsByVehicleAndBrandAndPrice(vehicle, brand, price);
    }

    @Override
    public Page<Car> getAllCarsPageable(Pageable pageable) {
        if (pageable.getPageSize() > 10) {
            pageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
        }
        return carRepository.findAll(pageable);
    }


    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(()
        -> new CarNotFoundException(id));
    }

    @Override
    public void save(@Valid CarCreateDto data) {
        try{
            carRepository.save(new Car(data));
        } catch (DataIntegrityViolationException ex){
            throw new CarDuplicateException(data.chassis());
        }
    }


    @Override
    public Car update(Long id, CarUpdateDto data) {
            Car entity = getById(id);
            updateData(entity, data);
            return carRepository.save(entity);
    }


    @Override
    public void delete(Long id) {
        if(getById(id) != null){
            carRepository.deleteById(id);
        }
    }

    @Override
    public void updateData(Car entity, CarUpdateDto data) {
        entity.setDescription(data.description());
        entity.setSold(data.sold());
        entity.setPrice(data.price());
    }
}
