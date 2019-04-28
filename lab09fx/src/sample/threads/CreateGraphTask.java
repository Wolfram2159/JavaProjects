package sample.threads;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

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
    public CreateGraphTask(Movie movie, Actor firstActor, Actor secondActor, Graph<Actor, Movie> g) {
        this.movie = movie;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.g = g;
        network = new NetworkFactory();
    }

    @Override
    protected Boolean call() throws Exception {
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
        }
        return false;
    }
}
