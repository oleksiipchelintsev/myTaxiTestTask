package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;

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

//    @Query(value = "SELECT  driver.id  FROM driver" +
//            "INNER JOIN car ON driver.car_id = car.id" +
//            "WHERE car.availability = false " +
//            "AND" +
//            " (car.license_plate = :#{#carDO.licensePlate}" +
//            " OR car.seat_count = :#{#carDO.seatCount} " +
//            " OR car.rating = :#{#carDO.rating} " +
//            " OR car.engine_type  = :#{#carDO.engineType} " +
//            " OR car.carrying_capacity  = :#{#carDO.carryingCapacity}" +
//            " OR car.manufacture.id  = :#{#carDO.manufacturer.name} )"
//            , nativeQuery = true)
//    List<DriverDO> findDriversByCarAttributesV2(@Param("carDO") final CarDO carDO);
//
//
//    @Query(value = "?", nativeQuery = true)
//    List<DriverDO> findDriversByCarAttributes(@Param("filterCarQuery") final String filterCarQuery);

    @Query(value = ":query", nativeQuery = true)
    List<DriverDO> findAllDriversByCarFilter(@Param("query") String query);
}
