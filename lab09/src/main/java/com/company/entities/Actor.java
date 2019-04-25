package com.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Actor.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "Actor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
