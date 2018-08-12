package com.mytaxi.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import com.mytaxi.util.FilterQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DefaultCarService implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public CarDO save(CarDO carDO) throws ConstraintsViolationException {
        if(carRepository.findById(carDO.getId()) == null){
            return carRepository.save(carDO);
        }
        return null;
    }

    @Override
    public CarDO update(CarDO carDO) throws EntityNotFoundException {
        if (carRepository.findById(carDO.getId()) != null) {
            return carRepository.save(carDO);
        }
        return null;
    }

    @Override
    public CarDO get(Long carId) throws EntityNotFoundException {
        return (CarDO) carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

    @Override
    public void delete(Long carId) throws EntityNotFoundException {
        carRepository.deleteById(carId);
    }

    @Override
    public List<CarDO> findAllCarsByAvailability(Boolean availability) {
        return carRepository.findAllCarsByAvailability(availability);
    }

    @Override
    public List<CarDO> findAllCarsByFilters(String jsonFilters) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String query = FilterQueryBuilder.getCarFilterQueryFromJson(objectMapper.readValue(jsonFilters, JsonNode.class));
        return carRepository.findAllCarsByFilter(query);
    }
}
