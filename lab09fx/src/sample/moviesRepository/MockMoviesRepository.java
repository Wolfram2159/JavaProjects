package sample.moviesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.entities.Actor;
import sample.entities.Movie;

public class MockMoviesRepository implements MoviesRepository {
    private List<Actor> actorList;
    private List<Movie> movieList;

    public MockMoviesRepository() {
        Actor a1 = new Actor("a1", "a1");
        Actor a2 = new Actor("a2", "a2");
        Actor a3 = new Actor("a3", "a3");
        Actor a4 = new Actor("a4", "a4");
        Actor a5 = new Actor("a5", "a5");
        Actor a6 = new Actor("a6", "a6");
        Actor a7 = new Actor("a7", "a7");
        Actor a8 = new Actor("a8", "a8");
        Actor a9 = new Actor("a9", "a9");
        Actor a10 = new Actor("a10", "a10");
        Actor a11 = new Actor("a11", "a11");
        Actor a12 = new Actor("a12", "a12");
        Actor a13 = new Actor("a13", "a13");
        Actor a14 = new Actor("a14", "a14");
        Actor a15 = new Actor("a15", "a15");
        Actor a16 = new Actor("a16", "a16");
        Actor a17 = new Actor("a17", "a17");
        Actor a18 = new Actor("a18", "a18");
        Actor a19 = new Actor("a19", "a19");

        actorList = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19);

        movieList = Arrays.asList(
                new Movie("m1", "m1", new Actor[]{a1, a2, a3}),
                new Movie("m2", "m2", new Actor[]{a1, a3}),
                new Movie("m3", "m3", new Actor[]{a1, a4}),
                new Movie("m4", "m4", new Actor[]{a2, a5, a6}),
                new Movie("m5", "m5", new Actor[]{a3, a7}),
                new Movie("m6", "m6", new Actor[]{a3, a8, a5}),
                new Movie("m7", "m7", new Actor[]{a4, a9}),
                new Movie("m8", "m8", new Actor[]{a10, a13}),
                new Movie("m9", "m9", new Actor[]{a10, a12, a11}),
                new Movie("m10", "m10", new Actor[]{a13, a12, a14, a15}),
                new Movie("m11", "m11", new Actor[]{a12, a16}),
                new Movie("m12", "m12", new Actor[]{a11, a17}),
                new Movie("m13", "m13", new Actor[]{a15, a18}),
                new Movie("m14", "m14", new Actor[]{a18, a19}),
                new Movie("m15", "m15", new Actor[]{a19, a5})
        );
    }

    @Override
    public Actor getActor(String id) {
        for (Actor actor : actorList) {
            if (actor.getId().equals(id)) {
                return actor;
            }
        }
        return null;
    }

    @Override
    public List<Movie> getActorMovies(String id) {
        List<Movie> actorMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            for (Actor actor : movie.getActors()) {
                if (actor.getId().equals(id)) {
                    actorMovies.add(movie);
                    break;
                }
            }
        }
        return actorMovies;
    }

    @Override
    public Movie getMovie(String id) {
        for (Movie movie : movieList) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<Actor> searchActor(String name) {
        for (Actor actor : actorList) {
            if (actor.getId().equals(name)){
                return Arrays.asList(actor);
            }
        }
        return null;
    }
}
