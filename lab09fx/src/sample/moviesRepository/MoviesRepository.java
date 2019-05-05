package sample.moviesRepository;

import java.util.List;

import sample.entities.Actor;
import sample.entities.Movie;

public interface MoviesRepository {
    Actor getActor(String id);//Method returns actor, not null fields
    List<Movie> getActorMovies(String id);//Method returns movie array with movie null fields
    Movie getMovie(String id);//Method returns movie, not null fields
    List<Actor> searchActor(String name);
}
