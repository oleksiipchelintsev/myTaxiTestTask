package com.mytaxi.service;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO selectCar(long carId, long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException;

    DriverDO deSelectCar(long carId, long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException;

    List<DriverDO> filterDriverByCar(String searchQuery) throws IOException;
}
