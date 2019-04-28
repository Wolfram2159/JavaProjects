package sample.network;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sample.entities.Movie;

public class NetworkFactory {
    private OkHttpClient client;

    public NetworkFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();
    }

    public String getActor(String actorId) throws IOException {
        //OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/"+actorId)
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        response.body().close();
        return json;
    }
    public String getMovie(String movieId) throws IOException {
        //OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/movies/"+movieId)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        response.body().close();
        return json;
    }
    public String getActorMovies(String actorId) throws IOException{
        //OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/"+actorId+"/movies")
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        response.body().close();
        return json;
    }
    public String searchActors(String pattern) throws IOException{
        //OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/search/"+pattern)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        response.body().close();
        return json;
    }
    public Movie completeMovie(Movie movie) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String movieJson = getMovie(movie.getId());
        return mapper.readValue(movieJson, Movie.class);
    }
}
