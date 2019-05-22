package com.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = LocalizationBody.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizationBody {
    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("frequency")
    private Integer frequency;

    public LocalizationBody() {
    }

    public LocalizationBody(String cityName, Double lat, Double lon, Integer frequency) {
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
        this.frequency = frequency;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
