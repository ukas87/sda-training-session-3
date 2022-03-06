package service;

import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackMapForecast;
import utils.objectConverter.ObjectConverter;
import utils.objectConverter.OpenWeatherMapToWeatherConverter;
import utils.objectConverter.WeatherStackToWeatherConverter;

public class WeatherService {

    WeatherClient<OpenWeatherMapForecast> openWeatherMapClient;
    WeatherClient<WeatherStackMapForecast> weatherStackClient;


    public Weather getWeatherByCity(String city) {
        Weather weather1 = getWeatherFromOpenWeatherMap(city);
        Weather weather2 = getWeatherFromWeatherStack(city);
        openWeatherMapClient.getWeatherByCity(city);


        return null;
    }

    private Weather getWeatherFromOpenWeatherMap(String city) {
        OpenWeatherMapForecast forecast = openWeatherMapClient.getWeatherByCity(city);
        ObjectConverter<OpenWeatherMapForecast, Weather> converter = OpenWeatherMapToWeatherConverter.getInstance();
        return converter.convert(forecast);
    }

    private Weather getWeatherFromWeatherStack(String city) {
        WeatherStackMapForecast forecast = weatherStackClient.getWeatherByCity(city);
        ObjectConverter<WeatherStackMapForecast, Weather> converter = WeatherStackToWeatherConverter.getInstance();
        return converter.convert(forecast);
    }


}
