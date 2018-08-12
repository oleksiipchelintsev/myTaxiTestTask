package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<CarDO, Long> {
    List<CarDO> findAllCarsByAvailability(Boolean availability);

    @Query(value = ":query", nativeQuery = true)
    List<CarDO> findAllCarsByFilter(@Param("query") String query);
}
