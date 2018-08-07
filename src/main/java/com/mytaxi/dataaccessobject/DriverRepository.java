package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;

import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Database Access Object for driver table.
 * <p/>
 */


public interface DriverRepository extends CrudRepository<DriverDO, Long>
{
    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    //Additional filtering is required once query results are obtained
    //if multiple attributes are searched at once.

    //SELECT drivers.driverID
    //FROM drivers
    //INNER JOIN cars ON drivers.carID = cars.carID;
    //WHERE     cars.license_plate = license_plate
    //      OR  cars.seat_count = seat_count
    //      OR  cars.engineType = license_plate
    //      OR  cars.license_plate = license_plate
    //      OR  cars.license_plate = license_plate
    //      OR  cars.license_plate = license_plate
    //Как это сделать граммотнее      Оно вообще заработает?????



    //Create myQueryBuilredClass.getQuery(carDO)           //Util
    @Query(value =  "SELECT d, c FROM CarDO c, DriverDO d " +
            "WHERE d.carId = c.id AND c.available = true " +
            "AND " +
            " (c.seatCount     = :#{#carDO.seatCount}" +
            " OR c.convertible = :#{#carDO.convertible} " +
            " OR c.rating = :#{#carDO.rating} " +
            " OR c.engineType  = :#{#carDO.engineType} " +
            " OR c.manufacturer.name  = :#{#carDO.manufacturer.name} )"
    , nativeQuery = true)
    List<DriverDO> findDriversByCarAttributes(CarDO carDO);
}
