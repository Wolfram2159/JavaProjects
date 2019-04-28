package com.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.jgrapht.graph.DefaultEdge;

import java.util.Arrays;

@JsonDeserialize(as = Movie.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends DefaultEdge {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("actors")
    private Actor[] actors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Actor[] getActors() {
        return actors;
    }

    public void setActors(Actor[] actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
