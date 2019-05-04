package sample.callbacks;

import java.util.List;

import sample.entities.Actor;
import sample.entities.Movie;

public interface FindConnectionCallback {
    void createGraphRepresentation(List<Actor> vertices, List<Movie> edges);
}
