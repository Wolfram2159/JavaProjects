package sample.threads;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.entities.Actor;
import sample.entities.Movie;
import sample.graph.GraphFinder;
import sample.network.NetworkFactory;

public class CreateGraphTask extends Task<Boolean> {

    private Movie movie;
    private Actor firstActor;
    private Actor secondActor;
    private Graph<Actor, Movie> g;
    private NetworkFactory network;
    private ObjectMapper mapper;
    private Integer i;
    public CreateGraphTask(Movie movie, Actor firstActor, Actor secondActor, Graph<Actor, Movie> g, Integer i) {
        this.movie = movie;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.g = g;
        this.i = i;
        network = new NetworkFactory();
        mapper = new ObjectMapper();
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        System.out.println("i=="+i);
    }

    @Override
    protected Boolean call() throws Exception {
        //System.out.println("First task");
        //System.out.println("jestem w tasku");
        movie = network.completeMovie(movie);
        for (Actor actor : movie.getActors()) {
            //System.out.println(actor.getId());
            g.addVertex(actor);
            g.addEdge(firstActor, actor, (Movie) movie.clone());
            if(actor.getId().equals(secondActor.getId())){
                Platform.runLater(() -> {
                    GraphFinder finder = new GraphFinder(g, firstActor,secondActor);
                    finder.findPath();
                });
                System.out.println("znaleziony");
                return true;
            }
            /*String json = network.getActorMovies(actor.getId());
            List<Movie> movieList = Arrays.asList(mapper.readValue(json, Movie[].class));
            for (Movie movie1 : movieList) {
                CreateSecondTask task = new CreateSecondTask(movie1, firstActor, secondActor, actor, g);
                new Thread(task).start();
            }*/
        }
        return false;
    }
}
