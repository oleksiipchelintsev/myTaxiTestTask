package com.mytaxi.service;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CarService {
    CarDO save(CarDO carDO) throws ConstraintsViolationException;

    CarDO update(CarDO updatedCarDO) throws EntityNotFoundException;

    CarDO get(Long carId) throws EntityNotFoundException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDO> findAllCarsByAvailability(Boolean availability);

    List<CarDO> findAllCarsByFilters(String jsonFilters) throws IOException;
}
