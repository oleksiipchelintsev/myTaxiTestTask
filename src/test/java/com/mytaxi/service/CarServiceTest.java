package com.mytaxi.service;


import com.mytaxi.MytaxiServerApplicantTestApplication;
import com.mytaxi.MytaxiServerApplicantTestApplicationTests;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.dataaccessobject.ManufactureRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufactureDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.impl.DefaultCarService;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;




@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@EntityScan(basePackages = {"com.mytaxi.domainobject"})
@ContextConfiguration(classes = MytaxiServerApplicantTestApplication.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CarServiceTest {

    CarDO carDOInDB;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CarService carService;

    @Autowired
    ManufactureRepository manufactureRepository;


    @Before
    public void setPreData() throws ConstraintsViolationException {
        //at least one Manufacturer
        ManufactureDO manufactureDO = new ManufactureDO();
        manufactureDO.setName("BMW");
        manufactureDO.setAdress("Germany: Munich");
        manufactureDO.setDateCreated(ZonedDateTime.now());
        manufactureRepository.save(manufactureDO);              //Для manufacture нет service? это нормлаьно???

        //Adding at least one Availiable car
        CarDO carDO = new CarDO();
        carDO.setAvailability(true);
        carDO.setCarryingCapacity(320);
        carDO.setConvertible(true);
        carDO.setDateCreated(ZonedDateTime.now()); //???
        carDO.setSeatCount(5);
        carDO.setRating(5.3);
        carDO.setManufactureDO(manufactureDO); //Что делаем тут???
        carDO.setEngineType(EngineType.DIESEL);
        carDO.setLicensePlate("NR4523399");
        carDOInDB = carService.save(carDO);
    }

    //
    //List<CarDO> findAllCarsByAvailability(Boolean availability);
    //List<CarDO> findAllCarsByFilters(String jsonFilters) throws IOException;
    //

    @Test
    public void testFindAllByAvailability() {
        List<CarDO> cars = carService.findAllCarsByAvailability(Boolean.TRUE);
        List<CarDO> unavailableCars = carService.findAllCarsByAvailability(Boolean.FALSE);
        assertNotNull(cars);
        assertNotNull(unavailableCars);
        assertEquals(1, cars.size());
        assertTrue(unavailableCars.isEmpty());
        assertEquals("BMW", cars.get(0).getManufactureDO().getName());
    }

    //
    //Alex make here
    // List<CarDO> findAllCarsByFilters(String jsonFilters) throws IOException;
    //

    @After
    public void cleanDB() throws EntityNotFoundException {
        carService.delete(carDOInDB.getId());
    }
}
