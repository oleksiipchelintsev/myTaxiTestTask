package com.mytaxi.domainobject;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name="manufacture")
public class ManufactureDO {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    @JsonProperty
    public String name;

    @Column
    @JsonProperty
    private String adress;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManufactureDO)) return false;
        ManufactureDO that = (ManufactureDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(name, that.name) &&
                Objects.equals(adress, that.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, name, adress);
    }

    @Override
    public String toString() {
        return "ManufactureDO{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
