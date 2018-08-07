package com.mytaxi.service.impl;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

import com.mytaxi.service.DriverService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarRepository carRepository;


    public DefaultDriverService(final DriverRepository driverRepository, final CarRepository carRepository)
    {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    @Override
    public DriverDO selectCar(long carId, long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException {
        CarDO carDO = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
        DriverDO driverDO = driverRepository.findById(driverId).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
        if(null != carDO && null !=driverDO  && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus())){
            if(carDO.getAvailability() && driverDO.getCarDO()==null){
                //driverDO.getCarDO().setAvailability(true);
                driverDO.setCarDO(carDO);
                carDO.setAvailability(false);
            } else {
                throw new CarAlreadyInUseException("Car is already taken by another driver");
            }
            carRepository.save(carDO);
            driverRepository.save(driverDO);
        } else if (null != carDO && null !=driverDO  && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()))
        {
            throw new DriverOfflineException("Driver is offline");
        }
        return driverDO;
    }

    @Override
    public DriverDO deSelectCar(long carId, long driverId) throws EntityNotFoundException, CarAlreadyInUseException, DriverOfflineException {
        CarDO carDO = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Could not find  Car entity with id: " + carId));
        DriverDO driverDO = driverRepository.findById(driverId).orElseThrow(() -> new EntityNotFoundException("Could not find DriverDO entity with id: " + driverId));
        if(null != carDO && null !=driverDO  && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus())){
            if(driverDO.getCarDO().getId() == carDO.getId()){
                driverDO.setCarDO(null);
                carDO.setAvailability(true);
            } else {
                throw new CarAlreadyInUseException("This car is taken by another driver");
            }
            carRepository.save(carDO);
            driverRepository.save(driverDO);
        } else if(null != carDO && null !=driverDO  && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus())){
            throw new DriverOfflineException("Driver is offline");
        }
        return driverDO;
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }
}
