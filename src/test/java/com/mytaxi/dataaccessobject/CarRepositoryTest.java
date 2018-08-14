package com.mytaxi.dataaccessobject;

import com.google.common.collect.Lists;
import com.mytaxi.MytaxiServerApplicantTestApplication;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufactureDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
//@EntityScan(basePackages = {"com.mytaxi.domainobject"})
//@ContextConfiguration(classes = {MytaxiServerApplicantTestApplication.class})
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    private Map<String, ManufactureDO> manufacturerDOMap = new HashMap<>();

    @Before
    public void addAndpersistListOfCarManufacturers() {
        ManufactureDO manufactureDO_1 = new ManufactureDO();
        manufactureDO_1.setName("BMW");
        manufactureDO_1.setAdress("Germany: Munich");
        manufactureDO_1.setDateCreated(ZonedDateTime.now());

        ManufactureDO manufactureDO_2 = new ManufactureDO();
        manufactureDO_2.setName("Mercedes-Benz");
        manufactureDO_2.setAdress("Germany: Stuttgart");
        manufactureDO_2.setDateCreated(ZonedDateTime.now());

        manufacturerDOMap.put("bmw", manufactureDO_1);
        manufacturerDOMap.put("mercedes", manufactureDO_2);

        manufactureRepository.save(manufactureDO_1);
        manufactureRepository.save(manufactureDO_2);
    }

    @Test
    public void shouldFetchAllRecordsFromDatabase() {

        List<CarDO> listOfCarsDO = createListOfCarsDO();
        listOfCarsDO.forEach((carDO) -> carRepository.save(carDO));

        ArrayList<CarDO> cars = Lists.newArrayList(carRepository.findAll());
        assertThat(cars).containsAll(listOfCarsDO);         //Alex все машины в базе являются машинами вне базы
    }

    public List<CarDO> createListOfCarsDO() {
        CarDO carDO_1 = new CarDO();
        carDO_1.setAvailability(true);
        carDO_1.setCarryingCapacity(320);
        carDO_1.setConvertible(true);
        carDO_1.setDateCreated(ZonedDateTime.now());    //??? Alex read about it
        carDO_1.setSeatCount(5);
        carDO_1.setRating(5.3);
        carDO_1.setManufactureDO(manufacturerDOMap.get("bmw"));
        carDO_1.setEngineType(EngineType.DIESEL);
        carDO_1.setLicensePlate("NR4523399");

        CarDO carDO_2 = new CarDO();
        carDO_2.setAvailability(true);
        carDO_2.setCarryingCapacity(270);
        carDO_2.setConvertible(false);
        carDO_2.setDateCreated(ZonedDateTime.now());
        carDO_2.setSeatCount(4);
        carDO_2.setRating(5.7);
        carDO_2.setManufactureDO(manufacturerDOMap.get("mercedes"));
        carDO_2.setEngineType(EngineType.DIESEL);
        carDO_2.setLicensePlate("NR4523382");

        carRepository.save(carDO_1);
        carRepository.save(carDO_2);

        return Arrays.asList(carDO_1, carDO_2);
    }
}
