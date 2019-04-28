package com.company.tools;

import com.company.entities.Movie;
import com.company.network.NetworkFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Toolbase {
    public static Movie completeMovie(Movie movie) throws IOException {
        NetworkFactory network = new NetworkFactory();
        ObjectMapper mapper = new ObjectMapper();
        String movieJson = network.getMovie(movie.getId());
        return mapper.readValue(movieJson, Movie.class);
    }
}
