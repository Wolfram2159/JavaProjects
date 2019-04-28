package com.company;

import com.company.entities.Actor;
import com.company.entities.Movie;
import com.company.network.NetworkFactory;
import com.company.threads.CreateGraphTask;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println("xd");
        String actorStart = "nm0000102";
        String actorStop = "nm1705014";
        ObjectMapper mapper = new ObjectMapper();
        NetworkFactory network = new NetworkFactory();
        List<Movie> firstList = null;
        Actor firstActor = null, secondActor = null;
        Graph<Actor, Movie> g = new SimpleGraph<>(Movie.class);
        g.addVertex(firstActor);
        try {
            String json = network.getActorMovies(actorStart);
            firstList = Arrays.asList(mapper.readValue(json, Movie[].class));
            json = network.getActor(actorStart);
            firstActor = mapper.readValue(json, Actor.class);
            json = network.getActor(actorStop);
            secondActor = mapper.readValue(json, Actor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Movie movie : firstList) {
            CreateGraphTask task = new CreateGraphTask(movie, firstActor, secondActor, g);
            task.setOnSucceeded(e -> {
                System.out.println("progres");
                if(task.getValue()){
                    System.out.println("jest ten sam");
                }
                new Thread(task).start();
            });
        }
        /*String actorStart = "nm0010611";
        Actor firstActor = null;
        String actorMovies = null;
        List<Movie> movieList = null;
        ObjectMapper mapper = new ObjectMapper();
        NetworkFactory network = new NetworkFactory();
        Actor secondActor = null;
        try {
            actorMovies = network.getActorMovies(actorStart);
            movieList = Arrays.asList(mapper.readValue(actorMovies, Movie[].class));
            actorMovies = network.getActor(actorStart);
            firstActor = mapper.readValue(actorMovies, Actor.class);
            String searchActor = network.getActor("nm0147147");
            secondActor = mapper.readValue(searchActor, Actor.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Graph<Actor, Movie> g = new SimpleGraph<>(Movie.class);
        g.addVertex(firstActor);
        //Actor secondActor = null;
        List<Movie> secondList = null;
        List<Movie> thirdList = null;
        Boolean second_loop = true;
        Boolean third_loop = true;
        double i = 0;
        double maxi = 0;
        first_loop:
        for (Movie movie : movieList) {
            //System.out.println("Movie 1: " + i);
            i++;
            maxi = movieList.size();
            try {
                movie = Toolbase.completeMovie(movie);
                for (Actor actor : movie.getActors()) {
                    if (!actor.getId().equals(firstActor.getId())) {
                        g.addVertex(actor);
                        g.addEdge(firstActor, actor, (Movie) movie.clone());
                        if (actor.getId().equals(secondActor.getId())) {
                            break first_loop;
                        }
                        try {
                            actorMovies = network.getActorMovies(actor.getId());
                            secondList = Arrays.asList(mapper.readValue(actorMovies, Movie[].class));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if (second_loop) {
                            double j = 0;
                            double maxj;
                            second_loop:
                            for (Movie movie1 : secondList) {
                                maxj = secondList.size();
                                System.out.println("Movie 1: " +  + (i/maxi)*100.0 +
                                        "  Movie 2: " + (j/maxj)*100.0);
                                //System.out.println("    Movie 2: " + j);
                                j++;
                                movie1 = Toolbase.completeMovie(movie1);
                                //System.out.println(movie1.getActors().length);
                                for (Actor movie1Actor : movie1.getActors()) {
                                    if (!movie1Actor.getId().equals(actor.getId())) {
                                        //System.out.println(movie1Actor.getId());
                                        g.addVertex(movie1Actor);
                                        g.addEdge(actor, movie1Actor, (Movie) movie1.clone());
                                        if (movie1Actor.getId().equals(secondActor.getId())) {
                                            second_loop = false;
                                            break second_loop;
                                        }
                                        *//*try {
                                            actorMovies = network.getActorMovies(movie1Actor.getId());
                                            thirdList = Arrays.asList(mapper.readValue(actorMovies, Movie[].class));
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        if (false) {
                                            double k = 0;
                                            double maxk = 0;
                                            third_loop:
                                            for (Movie movie2 : thirdList) {
                                                maxk = thirdList.size();

                                                k++;

                                                movie2 = Toolbase.completeMovie(movie2);
                                                for (Actor movie2Actor : movie2.getActors()) {
                                                    if (!movie2Actor.getId().equals(movie1Actor.getId())) {
                                                        g.addVertex(movie2Actor);
                                                        g.addEdge(movie1Actor, movie2Actor, (Movie) movie2.clone());
                                                        if (movie2Actor.getId().equals(secondActor.getId())) {
                                                            third_loop = false;
                                                            break third_loop;
                                                        }
                                                    }
                                                }
                                            }
                                        }*//*
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(firstActor, secondActor);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();
        for (int v = 0; v < actors.size(); ++v) {
            if (v == actors.size() - 1)
                System.out.print(actors.get(v));
            else
                System.out.print(actors.get(v) + " -> " + edges.get(v).toString() + " -> ");
        }
    }
}
