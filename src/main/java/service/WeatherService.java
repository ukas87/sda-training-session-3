package service;
import model.Location;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackForecast;
import utils.averager.Averager;
import utils.objectConverter.ObjectConverter;
import utils.objectConverter.OpenWeatherMapToWeatherConverter;
import utils.objectConverter.WeatherStackToWeatherConverter;

import java.time.LocalDateTime;

public class WeatherService {

    WeatherClient<OpenWeatherMapForecast> openWeatherMapClient;
    WeatherClient<WeatherStackForecast> weatherStackClient;
    Averager<Weather> weatherAverager;

    public WeatherService(WeatherClient<OpenWeatherMapForecast> openWeatherMapClient, WeatherClient<WeatherStackForecast> weatherStackClient, Averager<Weather> weatherAverager) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherStackClient = weatherStackClient;
        this.weatherAverager = weatherAverager;
    }

    public Weather getWeatherByCity(String city) {
        Weather weather1 = getWeatherFromOpenWeatherMap(city);
        Weather weather2 = getWeatherFromWeatherStack(city);

        Weather result = getAverageWeather(weather1,weather2);


        result.setDate(LocalDateTime.now().toLocalDate());
        result.setLocation(getStandardizedLocation());

        return result;
    }

    private Weather getWeatherFromOpenWeatherMap(String city) {
        OpenWeatherMapForecast forecast = openWeatherMapClient.getWeatherByCity(city);
        ObjectConverter<OpenWeatherMapForecast, Weather> converter = OpenWeatherMapToWeatherConverter.getInstance();
        return converter.convert(forecast);
    }

    private Weather getWeatherFromWeatherStack(String city) {
        WeatherStackForecast forecast = weatherStackClient.getWeatherByCity(city);
        ObjectConverter<WeatherStackForecast, Weather> converter = WeatherStackToWeatherConverter.getInstance();
        return converter.convert(forecast);
    }
    private Location getStandardizedLocation(Location...location){

        return null;
    }

    private Weather getAverageWeather(Weather...weathers){
        return weatherAverager.getAverage(weathers);
    }


}
