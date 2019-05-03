package sample.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.entities.Actor;
import sample.entities.Movie;
import sample.moviesRepository.MoviesRepository;

public class GraphFactory {
    private Graph<Actor, Movie> g;
    private Actor firstActor;
    private Actor secondActor;
    private MoviesRepository repository;
    private List<Actor> firstDegreeFromA;
    private List<Actor> firstDegreeFromB;
    private List<Movie> firstMoviesFromA;
    private List<Movie> firstMoviesFromB;

    private List<Actor> secondDegreeFromA;
    private List<Actor> secondDegreeFromB;
    private List<Movie> secondMoviesFromA;
    private List<Movie> secondMoviesFromB;

    private List<Actor> thirdDegreeFromA;
    private List<Actor> thirdDegreeFromB;
    private List<Movie> thirdMoviesFromA;
    private List<Movie> thirdMoviesFromB;

    private List<Actor> fourthDegreeFromA;
    private List<Actor> fourthDegreeFromB;
    private List<Movie> fourthMoviesFromB;
    private List<Movie> fourthMoviesFromA;


    public GraphFactory(String firstActor, String secondActor, MoviesRepository repository) {
        g = new SimpleGraph<>(Movie.class);
        this.repository = repository;
        this.firstActor = repository.getActor(firstActor);
        this.secondActor = repository.getActor(secondActor);
        secondDegreeFromA = new ArrayList<>();
        secondDegreeFromB = new ArrayList<>();
        secondMoviesFromA = new ArrayList<>();
        secondMoviesFromB = new ArrayList<>();
        thirdDegreeFromA = new ArrayList<>();
        thirdDegreeFromB = new ArrayList<>();
        thirdMoviesFromA = new ArrayList<>();
        thirdMoviesFromB = new ArrayList<>();
        fourthDegreeFromA = new ArrayList<>();
        fourthDegreeFromB = new ArrayList<>();
        fourthMoviesFromB = new ArrayList<>();
        fourthMoviesFromA = new ArrayList<>();
    }

    public void makeGraph() {
        g.addVertex(firstActor);
        g.addVertex(secondActor);

        firstDegreeFromA = Arrays.asList(firstActor);
        firstDegreeFromB = Arrays.asList(secondActor);

        firstMoviesFromA = checkMovies(firstActor);
        firstMoviesFromB = checkMovies(secondActor);

        if (createDegree(firstMoviesFromA, firstMoviesFromB, firstDegreeFromA, firstDegreeFromB,
                secondMoviesFromA, secondMoviesFromB, secondDegreeFromA, secondDegreeFromB, 1)) {
            if (createDegree(secondMoviesFromA, secondMoviesFromB, secondDegreeFromA, secondDegreeFromB,
                    thirdMoviesFromA, thirdMoviesFromB, thirdDegreeFromA, thirdDegreeFromB, 4)) {
                createDegree(thirdMoviesFromA, thirdMoviesFromB, thirdDegreeFromA, thirdDegreeFromB,
                        fourthMoviesFromA, fourthMoviesFromB, fourthDegreeFromA, fourthDegreeFromB, 7);
            }
        }
    }

    private Boolean createDegree(List<Movie> firstMoviesFromA, List<Movie> firstMoviesFromB,
                                 List<Actor> firstDegreeFromA, List<Actor> firstDegreeFromB,
                                 List<Movie> secondMoviesFromA, List<Movie> secondMoviesFromB,
                                 List<Actor> secondDegreeFromA, List<Actor> secondDegreeFromB,
                                 Integer degree) {
        System.out.println("Checking " + degree + "...");
        if (!findConnection(firstMoviesFromA, firstMoviesFromB, firstDegreeFromA, firstDegreeFromB)) {
            for (Actor actor : firstDegreeFromA) {
                secondDegreeFromA.addAll(addingLoop(actor));
            }
            for (Actor actor : firstDegreeFromB) {
                secondDegreeFromB.addAll(addingLoop(actor));
            }
            for (Actor actor : secondDegreeFromA) {
                secondMoviesFromA.addAll(checkMovies(actor));
            }
            System.out.println("Checking " + (degree + 1) + "...");
            if (!findConnection(secondMoviesFromA, firstMoviesFromB, secondDegreeFromA, firstDegreeFromB)) {
                for (Actor actor : secondDegreeFromB) {
                    secondMoviesFromB.addAll(checkMovies(actor));
                }
                System.out.println("Checking " + (degree + 2) + "...");
                return !findConnection(secondMoviesFromA, secondMoviesFromB, secondDegreeFromA, secondDegreeFromB);
            }
        }
        return false;
    }

    private List<Actor> addingLoop(Actor startActor) {
        g.addVertex(startActor);
        List<Actor> actorList = new ArrayList<>();
        for (Movie actorMovie : startActor.getMovies()) {
            actorMovie = repository.getMovie(actorMovie.getId());
            for (Actor actor : actorMovie.getActors()) {
                if (!actor.equals(startActor)) {
                    if (g.addVertex(actor)) {
                        actorList.add(actor);
                    }
                    g.addEdge(startActor, actor, (Movie) actorMovie.clone());
                }
            }
        }
        return actorList;
    }

    private List<Movie> checkMovies(Actor actor) {
        List<Movie> actorMovies = repository.getActorMovies(actor.getId());
        actor.setMovies(actorMovies);
        return actorMovies;
    }

    private Boolean findConnection(List<Movie> moviesFromA, List<Movie> moviesFromB, List<Actor> actorsFromA, List<Actor> actorsFromB) {
        for (Movie movieA : moviesFromA) {
            for (Movie movieB : moviesFromB) {
                if (movieA.getId().equals(movieB.getId())) {
                    movieA = repository.getMovie(movieA.getId());
                    List<Actor> movieActors = movieA.getActors();
                    Actor a = null;
                    Actor b = null;
                    for (Actor movieActor : movieActors) {
                        if (actorsFromA.contains(movieActor)) {
                            a = movieActor;
                        } else if (actorsFromB.contains(movieActor)) {
                            b = movieActor;
                        }
                    }
                    g.addEdge(a, b, (Movie) movieA.clone());
                    findShortestPath(firstActor, secondActor);
                    return true;
                }
            }
        }
        return false;
    }

    private void findShortestPath(Actor a, Actor b) {
        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(a, b);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();
        for (int i = 0; i < actors.size(); ++i) {
            if (i == actors.size() - 1)
                System.out.print(actors.get(i));
            else
                System.out.print(actors.get(i) + " -> " + edges.get(i).toString() + " -> ");
        }
    }
}
