package sample.threads;

import org.jgrapht.Graph;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.entities.Actor;
import sample.entities.Movie;
import sample.graph.GraphFinder;
import sample.network.NetworkFactory;

public class CreateSecondTask extends Task<Boolean> {
    private Movie movie;
    private Actor firstActor;
    private Actor secondActor;
    private Actor vertexActor;
    private Graph<Actor, Movie> g;
    private NetworkFactory network;

    public CreateSecondTask(Movie movie, Actor firstActor, Actor secondActor, Actor vertexActor, Graph<Actor, Movie> g) {
        this.movie = movie;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.vertexActor = vertexActor;
        this.g = g;
        network = new NetworkFactory();
    }

    @Override
    protected Boolean call() throws Exception {
        System.out.println("    Second task");
        movie = network.completeMovie(movie);
        for (Actor actor : movie.getActors()) {
            //System.out.println(actor.getId());
            g.addVertex(actor);
            g.addEdge(vertexActor, actor, (Movie) movie.clone());
            if(actor.getId().equals(secondActor.getId())){
                Platform.runLater(() -> {
                    GraphFinder finder = new GraphFinder(g, firstActor,secondActor);
                    finder.findPath();
                });
                System.out.println("znaleziony 2");
                return true;
            }
        }
        return false;
    }
}
