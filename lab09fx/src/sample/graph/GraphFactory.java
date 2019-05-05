package sample.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.callbacks.FindConnectionCallback;
import sample.callbacks.ProgressBarCallback;
import sample.callbacks.TextAreaCallback;
import sample.entities.Actor;
import sample.entities.Movie;
import sample.moviesRepository.MoviesRepository;

public class GraphFactory {
    private Graph<Actor, Movie> g;
    private Actor firstActor;
    private Actor secondActor;
    private MoviesRepository repository;

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
    private TextAreaCallback textAreaCallback;
    private ProgressBarCallback progressBarCallback;
    private FindConnectionCallback findConnectionCallback;

    public GraphFactory(Actor firstActor, Actor secondActor, MoviesRepository repository) {
        g = new SimpleGraph<>(Movie.class);
        this.repository = repository;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
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

    public void setTextAreaCallback(TextAreaCallback textAreaCallback) {
        this.textAreaCallback = textAreaCallback;
    }

    public void setProgressBarCallback(ProgressBarCallback progressBarCallback) {
        this.progressBarCallback = progressBarCallback;
    }

    public void setFindConnectionCallback(FindConnectionCallback findConnectionCallback) {
        this.findConnectionCallback = findConnectionCallback;
    }

    public void makeGraph() {
        g.addVertex(firstActor);
        g.addVertex(secondActor);

        textAreaCallback.addString("Making connection between " + firstActor.getName() + " and " + secondActor.getName());

        List<Actor> firstDegreeFromA = Arrays.asList(firstActor);
        List<Actor> firstDegreeFromB = Arrays.asList(secondActor);

        List<Movie> firstMoviesFromA = checkMovies(firstActor);
        List<Movie> firstMoviesFromB = checkMovies(secondActor);
        if (firstMoviesFromA.size()==0 || firstMoviesFromB.size()==0){
            textAreaCallback.addString("There is no connection between those actors. Choose other actors.");
        }else {
            textAreaCallback.addString("Checking first movies from A - first movies from B connections...");
            if (!findConnection(firstMoviesFromA, firstMoviesFromB, firstDegreeFromA, firstDegreeFromB)) {
                if (createDegree(firstMoviesFromB, firstDegreeFromA, firstDegreeFromB,
                        secondMoviesFromA, secondMoviesFromB, secondDegreeFromA, secondDegreeFromB,
                        "second", "first")) {
                    if (createDegree(secondMoviesFromB, secondDegreeFromA, secondDegreeFromB,
                            thirdMoviesFromA, thirdMoviesFromB, thirdDegreeFromA, thirdDegreeFromB,
                            "third", "second")) {
                        if (createDegree(thirdMoviesFromB, thirdDegreeFromA, thirdDegreeFromB,
                                fourthMoviesFromA, fourthMoviesFromB, fourthDegreeFromA, fourthDegreeFromB,
                                "fourth", "third")) {
                            textAreaCallback.addString("Nie ma żadnego połączenia pomiędzy tymi aktorami");
                        }
                    }
                }
            }
        }
    }

    private Boolean createDegree(List<Movie> firstMoviesFromB,
                                 List<Actor> firstDegreeFromA, List<Actor> firstDegreeFromB,
                                 List<Movie> secondMoviesFromA, List<Movie> secondMoviesFromB,
                                 List<Actor> secondDegreeFromA, List<Actor> secondDegreeFromB,
                                 String secondDegree, String firstDegree) {
        Double up = 0d;
        Double down;
        textAreaCallback.addString("    Creating " + secondDegree + " degree from A...");
        down = (double) firstDegreeFromA.size();
        for (Actor actor : firstDegreeFromA) {
            secondDegreeFromA.addAll(addingLoop(actor));
            progressBarCallback.setProgress(++up, down);
        }
        textAreaCallback.addString("    Creating " + secondDegree + " movies from A...");
        up = 0d;
        down = (double) secondDegreeFromA.size();
        for (Actor actor : secondDegreeFromA) {
            secondMoviesFromA.addAll(checkMovies(actor));
            progressBarCallback.setProgress(++up, down);
        }
        textAreaCallback.addString("Checking " + secondDegree + " movies from A - " + firstDegree + " movies from B connections...");
        if (!findConnection(secondMoviesFromA, firstMoviesFromB, secondDegreeFromA, firstDegreeFromB)) {
            textAreaCallback.addString("    Creating " + secondDegree + " degree from B...");
            up = 0d;
            down = (double) firstDegreeFromB.size();
            for (Actor actor : firstDegreeFromB) {
                secondDegreeFromB.addAll(addingLoop(actor));
                progressBarCallback.setProgress(++up, down);
            }
            textAreaCallback.addString("    Creating " + secondDegree + " movies from B...");
            up = 0d;
            down = (double) secondDegreeFromB.size();
            for (Actor actor : secondDegreeFromB) {
                secondMoviesFromB.addAll(checkMovies(actor));
                progressBarCallback.setProgress(++up, down);
            }
            textAreaCallback.addString("Checking " + secondDegree + " movies from A - " + secondDegree + " movies from B connections...");
            return !findConnection(secondMoviesFromA, secondMoviesFromB, secondDegreeFromA, secondDegreeFromB);
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
        progressBarCallback.setProgress(0d, 1d);
        return false;
    }

    private void findShortestPath(Actor a, Actor b) {
        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(a, b);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> vertices = shortestPath.getVertexList();
        progressBarCallback.setProgress(0d, 1d);
        findConnectionCallback.createGraphRepresentation(vertices, edges);
    }
}
