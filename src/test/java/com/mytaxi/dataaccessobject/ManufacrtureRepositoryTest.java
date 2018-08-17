package com.mytaxi.dataaccessobject;


import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufactureDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufacrtureRepositoryTest {

    @Autowired
    ManufactureRepository manufactureRepository;

    String name;
    String adress;
    ManufactureDO manufactureDO;

    @Before
    public void setPreData() throws ConstraintsViolationException {
        name = "Lexus";
        adress = "Japan: Nagoya";
        manufactureDO = new ManufactureDO();
        manufactureDO.setName(name);
        manufactureDO.setAdress(adress);
        manufactureRepository.save(manufactureDO);
    }

    @Test
    public void getManufactureByName() {
        ManufactureDO manufactureFromDatabase = manufactureRepository.findByName(name);
        assertThat(manufactureDO).isEqualTo(manufactureFromDatabase);
        manufactureRepository.delete(manufactureDO);
    }

    @Test
    public void getManufactureByAdress() {
        ManufactureDO manufactureFromDatabase = manufactureRepository.findByAdress(adress);
        assertThat(manufactureDO).isEqualTo(manufactureFromDatabase);
        manufactureRepository.delete(manufactureDO);
    }
}
