package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.WeatherDto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String appId = "22ca47167cd57b2121b0ead498c987cf";

    @Override
    public WeatherDto getWeatherByCity(String city) {
        ObjectNode node = getObjectNodeByCity(city);


        return WeatherDto.Builder()
                .withTemperature(node.get("main").get("temp").asDouble())
                .withPressure(node.get("main").get("pressure").asInt())
                .withHumidity(node.get("main").get("humidity").asInt())
                .withWindSpeed(node.get("wind").get("speed").asInt())
                .withWindDegrees(node.get("wind").get("deg").asInt())
                .withLatitude(node.get("coord").get("lat").asDouble())
                .withLongitude(node.get("coord").get("lon").asDouble())
                .withCityName(node.get("name").asText())
                .withCountryName(node.get("sys").get("country").asText())
                .build();
    }

    private ObjectNode getObjectNodeByCity(String city) {
        var url = buildRequestForCity(city);
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(url, ObjectNode.class);
        } catch (IOException e) {
            System.err.println("Unable to get forecast for your city");
        }
        return node;
    }

    private URL buildRequestForCity(String city) {
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&units=metric&appid=" + appId);
        } catch (MalformedURLException e) {
            System.err.println("Unable to create request");
        }
        return url;
    }

}
