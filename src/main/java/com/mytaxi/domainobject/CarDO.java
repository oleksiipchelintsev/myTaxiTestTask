package com.mytaxi.domainobject;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;


@Entity
@Table(name="car")
public class CarDO {

    @Id
    @Column(name="id")
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "license_plate")
    @JsonProperty
    private String licensePlate;

    @Column(name = "seat_count")
    @JsonProperty
    private Integer seatCount;

    @Column
    @JsonProperty
    private Boolean convertible;

    @Column
    @JsonProperty
    private Double rating;

    @Column(name = "engine_type")
    @JsonProperty
    private EngineType engineType;

    @Column(name = "carrying_capacity")
    @JsonProperty
    private Integer carryingCapacity;

    @ManyToOne(targetEntity = ManufactureDO.class)
    private ManufactureDO manufactureDO;

    @JsonProperty
    @Column(nullable=false, columnDefinition="boolean default true")
    private Boolean availability;

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Integer getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Integer carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public ManufactureDO getManufactureDO() {
        return manufactureDO;
    }

    public void setManufactureDO(ManufactureDO manufactureDO) {
        this.manufactureDO = manufactureDO;
    }
}
