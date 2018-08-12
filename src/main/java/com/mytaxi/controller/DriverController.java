package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.DriverService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.mytaxi.util.FilterQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    @PostMapping( value = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DriverDO> selectCar(@RequestParam long carId, @RequestParam long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException {
        DriverDO savedSpringDriverDO = driverService.selectCar(carId, driverId);
        if(savedSpringDriverDO != null) {
            return new ResponseEntity<DriverDO> (savedSpringDriverDO, HttpStatus.OK);
        }
        return new ResponseEntity<DriverDO>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping( value = "/deselect", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DriverDO> deSelectCar(@RequestParam long carId, @RequestParam long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException {
        DriverDO savedSpringDriverDO = driverService.deSelectCar(carId, driverId);
        if(savedSpringDriverDO != null) {
            return new ResponseEntity<DriverDO> (savedSpringDriverDO, HttpStatus.OK);
        }
        return new ResponseEntity<DriverDO>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping ( path = "/get-by-filters", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<List<DriverDO>> findAllDriversByCarFilters(@RequestHeader String jsonFilters) throws IOException {
        List<DriverDO> driversFromDB = driverService.filterDriverByCar(jsonFilters);

        if(driversFromDB != null) {
            return new ResponseEntity<List<DriverDO>>(driversFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
