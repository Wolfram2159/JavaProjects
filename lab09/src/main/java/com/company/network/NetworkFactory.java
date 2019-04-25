package com.company.network;

import com.company.entities.Actor;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkFactory {
    public static String getActor(String actorId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/"+actorId)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public static String getMovie(String movieId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/movies/"+movieId)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public static String getActorMovies(String actorId) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/"+actorId+"/movies")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public static String searchActors(String pattern) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://java.kisim.eu.org/actors/search/"+pattern)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
