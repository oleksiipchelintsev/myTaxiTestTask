package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.util.MapFilterCarDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends CrudRepository<CarDO, Long> {
    List<CarDO> findAllCarsByAvailability(Boolean availability);



    @Query(value =  "SELECT * FROM car " +
                    "WHERE car.license_plate LIKE :#{#filterCarDO.licensePlate} " +
                    "AND car.seat_count LIKE :#{#filterCarDO.seatCount} " +
                    "AND car.convertible LIKE :#{#filterCarDO.convertible} " +
                    "AND car.rating LIKE  :#{#filterCarDO.rating} " +
                    "AND car.engine_type LIKE :#{#filterCarDO.engineType} " +
                    "AND car.carrying_capacity LIKE :#{#filterCarDO.carryingCapacity} " +
                    "AND car.manufacturedo_id LIKE :#{#filterCarDO.manufactureDO} ", nativeQuery = true)
    List<CarDO> findAllCarsByFilter(@Param("filterCarDO") MapFilterCarDO filterCarDO);
}
