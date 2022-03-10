package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.WeatherDto;
import utils.FormatConverter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherStackClient implements WeatherClient {
    private static final String appId = "1dcafe969dd27e1888fc943455c556ed";


    @Override
    public WeatherDto getWeatherByCity(String city) {
        ObjectNode node = getObjectNodeByCity(city);


        return WeatherDto.Builder()
                .withTemperature(node.get("current").get("temperature").asDouble())
                .withPressure(node.get("current").get("pressure").asInt())
                .withHumidity(node.get("current").get("humidity").asInt())
                .withWindSpeed(FormatConverter.getInstance().fromKilometersHourToMeterSeconds(node.get("current").get("wind_speed").asInt()))
                .withWindDegrees(node.get("current").get("wind_degree").asInt())
                .withLatitude(node.get("location").get("lat").asDouble())
                .withLongitude(node.get("location").get("lon").asDouble())
                .withCityName(node.get("location").get("name").asText())
                .withCountryName(node.get("location").get("country").asText())
                .withRegion(node.get("location").get("region").asText())
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
            url = new URL("http://api.weatherstack.com/current?access_key=" + appId + "&query=" + city + "&units=m");
        } catch (MalformedURLException e) {
            System.err.println("Unable to create request");
        }
        System.out.println(url);
        return url;
    }

}

