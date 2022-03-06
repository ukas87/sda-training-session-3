package service;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackForecast;
import utils.objectConverter.ObjectConverter;
import utils.objectConverter.OpenWeatherMapToWeatherConverter;
import utils.objectConverter.WeatherStackToWeatherConverter;

public class WeatherService {

    WeatherClient<OpenWeatherMapForecast> openWeatherMapClient;
    WeatherClient<WeatherStackForecast> weatherStackClient;


    public Weather getWeatherByCity(String city) {
        Weather weather1 = getWeatherFromOpenWeatherMap(city);
        Weather weather2 = getWeatherFromWeatherStack(city);

        return getAverageWeather(weather1,weather2);
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

    private Weather getAverageWeather(Weather...weathers){
        return null;
    }


}
