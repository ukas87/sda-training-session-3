package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import model.WeatherDto;
import utils.FormatConverter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
public class WeatherStackClient implements WeatherClient {
    private static final String appId = "1dcafe969dd27e1888fc943455c556ed";

    @Override
    public WeatherDto getWeatherByCity(String city) {
        ObjectNode cityNode = getObjectNode(city);
        return buildDto(cityNode);
    }

    private ObjectNode getObjectNode(String city) {
        var url = buildUrl(city);
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(url, ObjectNode.class);
        } catch (IOException e) {
            log.error(e);
        }
        return node;
    }

    private URL buildUrl(String city) {
        URL url = null;
        try {
            url = new URL("https://api.weatherstack.com/current?access_key=" + appId + "&query=" + city + "&units=m");
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

    private ObjectNode getObjectNode(Double lat, Double lon) {
        URL url = buildUrl(lat, lon);
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(url, ObjectNode.class);
        } catch (IOException e) {
            log.error(e);
        }
        return node;
    }

    private URL buildUrl(Double lat, Double lon) {
        URL url = null;
        try {
            url = new URL("https://api.weatherstack.com/current?access_key=" + appId + "&query=" + lat + "," + lon + "&units=m");
        } catch (MalformedURLException e) {
            log.error(e);
        }
        log.info(url);
        return url;
    }

    private WeatherDto buildDto(ObjectNode node) {
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

}

