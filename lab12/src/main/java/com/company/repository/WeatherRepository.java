package com.company.repository;

import com.company.entities.Location;
import com.company.entities.Temperature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRepository {

    private OkHttpClient client;
    private ObjectMapper mapper;
    private final String appid = "appid=0e49525099bb4162eb72d1925b2812ee";
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    public WeatherRepository() {
        mapper = new ObjectMapper();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();
    }

    public Location getLocalization(String cityName){
        try {
            Request request = new Request.Builder()
                    .url(url + "?q=" + cityName + "&" + appid)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return mapper.readValue(json, Location.class);
        } catch (IOException ex) {
            System.out.println("getLocation IOException");
            return null;
        }
    }
    public Location getLocalization(Double lon, Double lat){
        try {
            Request request = new Request.Builder()
                    .url(url + "?lat=" + lat + "&lon=" + lon + "&" + appid)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return mapper.readValue(json, Location.class);
        } catch (IOException ex) {
            System.out.println("getLocation IOException");
            return null;
        }
    }
    public Temperature getTemp(Double lat, Double lon){
        try {
            Request request = new Request.Builder()
                    .url(url + "?lat=" + lat + "&lon=" + lon + "&" + appid)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            response.body().close();
            return mapper.readValue(json, Temperature.class);
        } catch (IOException ex) {
            System.out.println("getLocation IOException");
            return null;
        }
    }
}
