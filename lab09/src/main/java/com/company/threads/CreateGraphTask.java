package com.company.threads;

import com.company.entities.Actor;
import com.company.entities.Movie;
import com.company.tools.Toolbase;

import org.jgrapht.Graph;

import javafx.concurrent.Task;

public class CreateGraphTask extends Task<Boolean> {
    private Movie movie;
    private Actor firstActor;
    private Actor secondActor;
    private Graph<Actor, Movie> g;

    public CreateGraphTask(Movie movie, Actor firstActor, Actor secondActor, Graph<Actor, Movie> g) {
        this.movie = movie;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.g = g;
    }

    @Override
    protected Boolean call() throws Exception {
        movie = Toolbase.completeMovie(movie);
        for (Actor actor : movie.getActors()) {
            if(!actor.getId().equals(secondActor.getId())){
                g.addVertex(actor);
                g.addEdge(firstActor, actor, (Movie) movie.clone());
            }else{
                return true;
            }
        }
        return false;
    }
}
