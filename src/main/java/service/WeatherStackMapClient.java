package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.weatherstack.WeatherStackMapForecast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherStackMapClient implements WeatherClient<WeatherStackMapForecast> {
    private static final String appId = "1dcafe969dd27e1888fc943455c556ed";


    @Override
    public WeatherStackMapForecast getWeatherByCity(String country) {
        var url = buildRequestForCity(country);
        WeatherStackMapForecast forecast = null;
        try {
            forecast = new ObjectMapper().readValue(url, WeatherStackMapForecast.class);
        } catch (IOException e) {
            System.err.println("Unable to get forecast for your city");
        }
        return forecast;
    }

    private URL buildRequestForCity(String city){
        URL url = null;
        try {
            url = new URL("http://api.weatherstack.com/current?access_key=" + appId + "&query=" + city);
        } catch (MalformedURLException e) {
            System.err.println("Unable to create request");
        }
        System.out.println(url);
        return url;
    }

    }

