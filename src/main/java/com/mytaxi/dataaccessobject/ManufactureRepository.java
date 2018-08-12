package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.ManufactureDO;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for manufucture table.
 * <p/>
 */
    //@Repository         //Alex what is it?
    //@ConditionalOnProperty(value = "feature.car", havingValue = "true")  //Alex what is it?
    public interface ManufactureRepository extends CrudRepository<ManufactureDO, Long> {
        //ManufactureDO findByName(final String name);
}
