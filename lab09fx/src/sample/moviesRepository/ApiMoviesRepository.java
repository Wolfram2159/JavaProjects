package sample.moviesRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sample.entities.Actor;
import sample.entities.Movie;

public class ApiMoviesRepository implements MoviesRepository {
    private OkHttpClient client;
    private ObjectMapper mapper;

    public ApiMoviesRepository() {
        mapper = new ObjectMapper();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();
    }

    @Override
    public Actor getActor(String id) {
        try {
            Request request = new Request.Builder()
                    .url("https://java.kisim.eu.org/actors/" + id)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return mapper.readValue(json, Actor.class);
        } catch (IOException ex) {
            System.out.println("getActor IOException");
            return null;
        }
    }

    @Override
    public List<Movie> getActorMovies(String id) {
        try {
            Request request = new Request.Builder()
                    .url("https://java.kisim.eu.org/actors/" + id + "/movies")
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            List<Movie> actorMovies = Arrays.asList(mapper.readValue(json, Movie[].class));
            return actorMovies;
        } catch (IOException ex) {
            System.out.println("getActorMovies IOException");
            return null;
        }
    }

    @Override
    public Movie getMovie(String id) {
        try {
            Request request = new Request.Builder()
                    .url("https://java.kisim.eu.org/movies/" + id)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return mapper.readValue(json, Movie.class);
        } catch (IOException ex) {
            System.out.println("getMovie IOException");
            return null;
        }
    }
    @Override
    public List<Actor> searchActor(String name) {
        try {
            Request request = new Request.Builder()
                    .url("https://java.kisim.eu.org/actors/search/" + name)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return Arrays.asList(mapper.readValue(json, Actor[].class));

        }catch (IOException ex){
            System.out.println("searchActor IOException");
            return null;
        }
    }
}
