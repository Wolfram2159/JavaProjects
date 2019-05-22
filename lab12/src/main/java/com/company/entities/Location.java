package com.company.entities;


import com.company.entities.jsonDeserializers.LocalizationDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "localizations")
@JsonDeserialize(using = LocalizationDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer location_id;
    @Column(name = "lon")
    @JsonProperty("lon")
    private Double lon;
    @Column(name = "lat")
    @JsonProperty("lat")
    private Double lat;

    public Location() {
    }

    public Location(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Location(Integer location_id, Double lon, Double lat) {
        this.location_id = location_id;
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}