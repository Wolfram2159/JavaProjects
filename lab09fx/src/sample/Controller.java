package sample;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import sample.entities.Actor;
import sample.entities.Movie;
import sample.network.NetworkFactory;
import sample.threads.CreateGraphTask;

public class Controller {

    private volatile int i;

    public void onClick(ActionEvent actionEvent) {
        System.out.println("xd");
        String actorStart = "nm0000102";
        String actorStop = "nm4059010";
        ObjectMapper mapper = new ObjectMapper();
        NetworkFactory network = new NetworkFactory();
        List<Movie> firstList = null;
        Actor firstActor = null, secondActor = null;
        Graph<Actor, Movie> g = new SimpleGraph<>(Movie.class);
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
        List<Thread> threadList = new ArrayList<>();
        g.addVertex(firstActor);
        i = firstList.size();
        for (Movie movie : firstList) {
            //System.out.println(movie.getId());

            CreateGraphTask task = new CreateGraphTask(movie, firstActor, secondActor, g,i);
            i--;
            task.setOnSucceeded(e -> {
                //System.out.println("progres");
                //i--;
                System.out.println(i);
                if(task.getValue()){
                    System.out.println(movie.getId());
                }
            });
            Thread thread = new Thread(task);
            thread.start();
            //thread.start();
            //threadList.add(thread);
            /*if (firstList.indexOf(movie)==firstList.size()-1){
                try {
                    thread.join();
                    //System.out.println("poczekalem");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        }
        /*for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        //g.addVertex(secondActor);
        /*GraphFinder finder = new GraphFinder(g, firstActor, secondActor);
        finder.findPath();*/
    }
}
