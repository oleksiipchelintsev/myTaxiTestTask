package com.mytaxi.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mytaxi.domainobject.CarDO;

import java.util.HashMap;
import java.util.Map;

public class FilterQueryBuilder {

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
    public static String getDriverByCarQueryFromJson(JsonNode jsonNode){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT driver.id FROM driver INNER JOIN car ON driver.car_id = car.id WHERE car.availability = 'false' AND (");
        stringBuilder.append(getBaseFilterConditionFromJson(jsonNode));
        return stringBuilder.toString();
    }

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
    public static String getCarFilterQueryFromJson(JsonNode jsonNode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM car WHERE ");
        stringBuilder.append(getBaseFilterConditionFromJson(jsonNode));
        return stringBuilder.toString();
    }


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
    public static String getBaseFilterConditionFromJson(JsonNode jsonNode) {
        StringBuilder stringBuilder = new StringBuilder();
        if(jsonNode.fields().hasNext()){
            Map<String, String> nodes = (HashMap<String, String>)jsonNode.fields();
            for(String key : nodes.keySet()){
                switch (key) {
                    case "license_plate":  stringBuilder.append("car.license_plate = ").append(jsonNode.get("license_plate"));
                        break;
                    case "seat_count":  stringBuilder.append("AND car.seat_count = ").append(jsonNode.get("seat_count"));
                        break;
                    case "convertible":  stringBuilder.append("AND car.convertible = ").append(jsonNode.get("convertible"));
                        break;
                    case "rating": stringBuilder.append("AND car.rating = ").append(jsonNode.get("rating"));
                        break;
                    case "engine_type":  stringBuilder.append("AND car.engine_type = ").append(jsonNode.get("engine_type"));
                        break;
                    case "carrying_capacity":  stringBuilder.append("AND car.carrying_capacity = ").append(jsonNode.get("carrying_capacity"));
                        break;
                    case "manufacture_id":  stringBuilder.append("AND car.manufacture_id = ").append(jsonNode.get("manufacture_id"));
                        break;
                }
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
