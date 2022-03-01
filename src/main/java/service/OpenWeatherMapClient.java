package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.openweathermap.OpenWeatherMapForecast;

import java.io.IOException;
import java.net.URL;

public class OpenWeatherMapClient {

    private final String appId = "22ca47167cd57b2121b0ead498c987cf";

    public OpenWeatherMapForecast getResponse(String message) throws IOException {
        var url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message +
                "&units=metric&appid=" + appId);
        return new ObjectMapper().readValue(url, OpenWeatherMapForecast.class);
    }
}
