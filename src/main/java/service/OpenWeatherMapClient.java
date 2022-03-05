package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openweathermap.OpenWeatherMapForecast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMapClient implements WeatherClient<OpenWeatherMapForecast>{
    private static final String appId = "22ca47167cd57b2121b0ead498c987cf";

    @Override
    public OpenWeatherMapForecast getWeatherByCity(String country) {
        var url = buildRequestForCity(country);
        OpenWeatherMapForecast forecast = null;
        try {
            forecast = new ObjectMapper().readValue(url, OpenWeatherMapForecast.class);
        } catch (IOException e) {
            System.err.println("Unable to get forecast for your city");
        }
        return forecast;
    }

    private URL buildRequestForCity(String city){
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&units=metric&appid=" + appId);
        } catch (MalformedURLException e) {
            System.err.println("Unable to create request");
        }
        System.out.println(url);
        return url;
    }

}
