package com.mytaxi.util;

import com.mytaxi.domainobject.CarDO;

public class MapFilterCarDO {

    private String id;
    private String dateCreated;
    private String licensePlate;
    private String seatCount;
    private String convertible;
    private String rating;
    private String engineType;
    private String carryingCapacity;
    private String manufactureDO;
    private String availability;

    public MapFilterCarDO(CarDO carDO) {
        id = carDO.getId().toString() !=null ? carDO.getId().toString() : "%";
        dateCreated = carDO.getDateCreated().toString()  !=null ? carDO.getDateCreated().toString() : "%";
        licensePlate = carDO.getLicensePlate().toString()  !=null ? carDO.getLicensePlate().toString() : "%";
        seatCount = carDO.getSeatCount().toString()  !=null ? carDO.getSeatCount().toString() : "%";
        convertible = carDO.getConvertible().toString()  !=null ? carDO.getConvertible().toString() : "%";
        rating = carDO.getRating().toString()  !=null ? carDO.getRating().toString() : "%";
        engineType = carDO.getEngineType().ordinal()  !=0l ? String.valueOf(carDO.getEngineType().ordinal()) : "%";
        carryingCapacity = carDO.getCarryingCapacity().toString()  !=null ? carDO.getCarryingCapacity().toString() : "%";
        manufactureDO = carDO.getManufactureDO().getId().toString()  !=null ? carDO.getManufactureDO().getId().toString() : "%";
        availability = carDO.getAvailability().toString()  !=null ? carDO.getAvailability().toString() : "%";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getConvertible() {
        return convertible;
    }

    public void setConvertible(String convertible) {
        this.convertible = convertible;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(String carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public String getManufactureDO() {
        return manufactureDO;
    }

    public void setManufactureDO(String manufactureDO) {
        this.manufactureDO = manufactureDO;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "MapFilterCarDO{" +
                "id='" + id + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", seatCount='" + seatCount + '\'' +
                ", convertible='" + convertible + '\'' +
                ", rating='" + rating + '\'' +
                ", engineType='" + engineType + '\'' +
                ", carryingCapacity='" + carryingCapacity + '\'' +
                ", manufactureDO='" + manufactureDO + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
