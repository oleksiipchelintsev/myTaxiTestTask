package com.mytaxi.dataaccessobject;

import com.google.common.collect.Lists;
import com.mytaxi.MytaxiServerApplicantTestApplication;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufactureDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import com.mytaxi.util.MapFilterCarDO;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    private Map<String, ManufactureDO> manufacturerDOMap = new HashMap<>();

    ManufactureDO manufactureDO_1;
    ManufactureDO manufactureDO_2;

    @Before
    public  void addAndpersistListOfCarManufacturers() {
        manufactureDO_1 = new ManufactureDO();
        manufactureDO_1.setName("BMW");
        manufactureDO_1.setAdress("Germany: Munich");
        manufactureDO_1.setDateCreated(ZonedDateTime.now());

        manufactureDO_2 = new ManufactureDO();
        manufactureDO_2.setName("Mercedes-Benz");
        manufactureDO_2.setAdress("Germany: Stuttgart");
        manufactureDO_2.setDateCreated(ZonedDateTime.now());

        manufacturerDOMap.put("bmw", manufactureDO_1);
        manufacturerDOMap.put("mercedes", manufactureDO_2);

        manufactureRepository.save(manufactureDO_1);
        manufactureRepository.save(manufactureDO_2);
    }

    @Test
    public void getAllRecordsAndCompare() {
        List<CarDO> listOfCarsDO = createListOfCarsDO();
        listOfCarsDO.forEach((carDO) -> carRepository.save(carDO));
        ArrayList<CarDO> cars = Lists.newArrayList(carRepository.findAll());
        assertThat(cars).containsAll(listOfCarsDO);
    }



    @Test
    public void findAllAvailabileCars(){
        List<CarDO> availiableCars =  carRepository.findAllCarsByAvailability(true);
        availiableCars.forEach(car-> System.out.println(car));
        Assert.assertEquals(2,availiableCars.size());
    }


    @Test
    public void findAllUnAvailabileCars(){
        List<CarDO> unAvailiableCars =  carRepository.findAllCarsByAvailability(false);
        Assert.assertEquals(0,unAvailiableCars.size());
    }

    @Test
    public void findAllCarsByFilter(){
        MapFilterCarDO mapFilterCarDO = new MapFilterCarDO(createListOfCarsDO().get(0));
        mapFilterCarDO.setId("1");
        List<CarDO> filteredCars = carRepository.findAllCarsByFilter(mapFilterCarDO);
        assertThat(filteredCars.get(0)).isEqualTo(carRepository.findById(1L).get());
    }


    public List<CarDO> createListOfCarsDO() {
        CarDO carDO_1 = new CarDO();
        carDO_1.setAvailability(true);
        carDO_1.setCarryingCapacity(320);
        carDO_1.setConvertible(true);
        carDO_1.setDateCreated(ZonedDateTime.now());
        carDO_1.setSeatCount(5);
        carDO_1.setRating(5.3);
        carDO_1.setManufactureDO(manufacturerDOMap.get("bmw"));
        System.out.println("createListOfCarsDO" + carDO_1.getManufactureDO().getId());
        carDO_1.setEngineType(EngineType.DIESEL);
        carDO_1.setLicensePlate("NR4523399");
        carDO_1.setId(1L);


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
        carDO_2.setId(2L);

        carRepository.save(carDO_1);
        carRepository.save(carDO_2);

        return Arrays.asList(carDO_1, carDO_2);
    }
}
