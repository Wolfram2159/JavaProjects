package sample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.jgrapht.graph.DefaultEdge;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(as = Movie.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends DefaultEdge {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("actors")
    private Actor[] actors;

    public Movie() {
    }

    public Movie(String id, String title, Actor[] actors) {
        this.id = id;
        this.title = title;
        this.actors = actors;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Actor> getActors() {
        return Arrays.asList(actors);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
