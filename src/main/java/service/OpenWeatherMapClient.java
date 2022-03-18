package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import model.WeatherDto;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
public class OpenWeatherMapClient implements WeatherClient {
    private static final String appId = "22ca47167cd57b2121b0ead498c987cf";

    @Override
    public WeatherDto getWeatherByCity(String city) {
        ObjectNode cityNode = getObjectNode(city);
        return buildDto(cityNode);
    }

    private ObjectNode getObjectNode(String city)  {
        URL url = buildUrl(city);
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(url, ObjectNode.class);
        } catch (IOException e) {
            log.error(e);
        }
        return node;
    }

    private URL buildUrl(String city){
        URL url = null;
        try {
            url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&units=metric&appid=" + appId);
        } catch (MalformedURLException e) {
            log.error(e);
        }
        log.info(url);
        return url;
    }

    @Override
    public WeatherDto getWeatherByCoordinates(Double lat, Double lon) {
        ObjectNode coordinatesNode = getObjectNode(lat, lon);
        return buildDto(coordinatesNode);
    }

    private ObjectNode getObjectNode(Double lat, Double lon)  {
        URL url = buildUrl(lat, lon);
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(url, ObjectNode.class);
        } catch (IOException e) {
            log.error(e);
        }
        return node;
    }

    private URL buildUrl(Double lat, Double lon){
        URL url = null;
        try {
            url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&units=metric&appid=" + appId);
        } catch (MalformedURLException e) {
            log.error(e);
        }
        log.info(url);
        return url;
    }

    private WeatherDto buildDto(ObjectNode node) {
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
}
