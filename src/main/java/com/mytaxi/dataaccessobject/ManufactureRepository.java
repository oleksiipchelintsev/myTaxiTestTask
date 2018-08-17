package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.ManufactureDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Database Access Object for manufucture table.
 * <p/>
 */

@Repository
public interface ManufactureRepository extends CrudRepository<ManufactureDO, Long> {
        ManufactureDO findByName(final String name);
        ManufactureDO findByAdress(final String name);
}
