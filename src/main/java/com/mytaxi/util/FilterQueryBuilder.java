package com.mytaxi.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mytaxi.dataaccessobject.ManufactureRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class FilterQueryBuilder {

    @Autowired
    static ManufactureRepository manufactureRepository;

    /**
     *
     * @param jsonNode this argument is responsible for input parameters in JSON object
     *                  params are :  license_plate
     *                                seat_count
     *                                convertible
     *                                rating
     *                                engine_type
     *                                carrying_capacity
     *                                manufacture_id
     * @return
     */
    public static CarDO getBaseCarFilterConditionFromJson(JsonNode jsonNode) {
        CarDO carDO = new CarDO();
        if(jsonNode.fields().hasNext()){
            Map<String, String> nodes = (HashMap<String, String>)jsonNode.fields();
            for(String key : nodes.keySet()){
                switch (key) {
                    case "license_plate":  carDO.setLicensePlate(jsonNode.get("license_plate").toString());
                        break;
                    case "seat_count":   carDO.setSeatCount(Integer.parseInt(jsonNode.get("seat_count").toString()));
                        break;
                    case "convertible":  carDO.setConvertible(Boolean.parseBoolean(jsonNode.get("convertible").toString()));
                        break;
                    case "rating": carDO.setRating(Double.parseDouble(jsonNode.get("rating").toString()));
                        break;
                    case "engine_type":  carDO.setEngineType(EngineType.valueOf(jsonNode.get("engine_type").toString()));
                        break;
                    case "carrying_capacity":  carDO.setCarryingCapacity(Integer.parseInt(jsonNode.get("carrying_capacity").toString()));
                        break;
                    case "manufacture_id":  carDO.setManufactureDO(manufactureRepository.
                                                                    findById(Long.parseLong(jsonNode.get("manufacture_id").toString())).get());
                        break;
                }
            }
        }
        return carDO;
    }
}
