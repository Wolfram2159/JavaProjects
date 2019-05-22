package com.company.entities;

import com.company.entities.jsonDeserializers.TemperatureDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Temperatures")
@JsonDeserialize(using = TemperatureDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "temp_id")
    private Integer temp_id;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location_id;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "date")
    private Integer date;

    public Temperature() {
    }

    public Temperature(Double temperature, Integer date) {
        this.temperature = temperature;
        this.date = date;
    }

    public Temperature(Location location_id, Double temperature, Integer date) {
        this.location_id = location_id;
        this.temperature = temperature;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temp_id=" + temp_id +
                ", location_id=" + location_id +
                ", temperature=" + temperature +
                ", date=" + date +
                '}';
    }

    public Integer getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(Integer temp_id) {
        this.temp_id = temp_id;
    }

    public Location getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Location location_id) {
        this.location_id = location_id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
