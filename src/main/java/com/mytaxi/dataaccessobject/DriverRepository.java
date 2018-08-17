package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;

import com.mytaxi.util.MapFilterCarDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Database Access Object for driver table.
 * <p/>
 */

@Repository
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{
    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    @Query(value =  "SELECT driver.id FROM driver INNER JOIN car ON driver.car_id = car.id   " +
            "WHERE car.availability = 'false' " +
            "AND car.license_plate LIKE :#{#filterCarDO.licensePlate} " +
            "AND car.seat_count LIKE :#{#filterCarDO.seatCount} " +
            "AND car.convertible LIKE :#{#filterCarDO.convertible} " +
            "AND car.rating LIKE  :#{#filterCarDO.rating} " +
            "AND car.engine_type LIKE :#{#filterCarDO.engineType} " +
            "AND car.carrying_capacity LIKE :#{#filterCarDO.carryingCapacity} " +
            "AND car.manufacturedo_id LIKE :#{#filterCarDO.manufactureDO} ", nativeQuery = true)
    List<DriverDO> findAllDriversByCarFilter(@Param("filterCarDO") MapFilterCarDO filterCarDO);
}
