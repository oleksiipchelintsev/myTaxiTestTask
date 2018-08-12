package com.mytaxi.controller;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/v1/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<CarDO> saveCar(@RequestBody CarDO carDO) throws ConstraintsViolationException {
        CarDO carFromDB = carService.save(carDO);
        if (carFromDB != null) {
            return new ResponseEntity<CarDO>(carDO, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<CarDO> updateCar(@RequestBody CarDO carDO) throws EntityNotFoundException {
        CarDO carFromDB = carService.update(carDO);
        if (carFromDB != null) {
            return new ResponseEntity<CarDO>(carFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<CarDO> deleteCar(@RequestBody CarDO carDO) throws EntityNotFoundException {
        carService.delete(carDO.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping( path = "/get-one", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<CarDO> getCar(@RequestHeader Long carID) throws EntityNotFoundException {
        CarDO carFromDB = carService.get(carID);
        if(carFromDB != null) {
            return new ResponseEntity<CarDO>(carFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping ( path = "/get-by-availability", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    ResponseEntity<List<CarDO>> findAllCarsByAvailability(@RequestHeader Boolean availability){
        List<CarDO> carsFromDB = carService.findAllCarsByAvailability(availability);

        if(carsFromDB != null) {
            return new ResponseEntity<List<CarDO>>(carsFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping ( path = "/get-by-filters", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<List<CarDO>> findAllCarsByFilters(@RequestHeader String jsonFilters) throws IOException {
        List<CarDO> carsFromDB = carService.findAllCarsByFilters(jsonFilters);

        if(carsFromDB != null) {
            return new ResponseEntity<List<CarDO>>(carsFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
