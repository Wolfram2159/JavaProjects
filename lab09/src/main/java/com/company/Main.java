package com.company;

import com.company.entities.Movie;
import com.company.network.NetworkFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("xd");
        try {
            String xd = NetworkFactory.getActorMovies("nm0000102");
            System.out.println(xd);
            ObjectMapper mapper = new ObjectMapper();
            //ActorMovies[] object = new ObjectMapper().readValue(xd,ActorMovies[].class);
            List<Movie> actorList = Arrays.asList(mapper.readValue(xd, Movie[].class));
            System.out.println(actorList.size());
            System.out.println(actorList.get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
