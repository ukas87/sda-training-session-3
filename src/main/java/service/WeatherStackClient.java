package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.WeatherDto;
import model.weatherstack.WeatherStackForecast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherStackClient implements WeatherClient {
    private static final String appId = "1dcafe969dd27e1888fc943455c556ed";


    @Override
    public WeatherDto getWeatherByCity(String country) {
        var url = buildRequestForCity(country);
        WeatherStackForecast forecast = null;
        try {
            forecast = new ObjectMapper().readValue(url, WeatherStackForecast.class);
        } catch (IOException e) {
            System.err.println("Unable to get forecast for your city");
        }
        return null;
    }

    private URL buildRequestForCity(String city) {
        URL url = null;
        try {
            url = new URL("http://api.weatherstack.com/current?access_key=" + appId + "&query=" + city + "&units=m");
        } catch (MalformedURLException e) {
            System.err.println("Unable to create request");
        }
        System.out.println(url);
        return url;
    }

}

