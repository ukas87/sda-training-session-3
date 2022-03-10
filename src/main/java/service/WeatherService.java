package service;
import dto.Mapper;
import dto.WeatherDto;
import model.Location;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackForecast;
import utils.averager.Averager;
import java.time.LocalDateTime;

public class WeatherService {

    final WeatherClient<OpenWeatherMapForecast> openWeatherMapClient;
    final WeatherClient<WeatherStackForecast> weatherStackClient;
    final Averager<Weather> weatherAverager;
    final Mapper mapper;

    public WeatherService(WeatherClient<OpenWeatherMapForecast> openWeatherMapClient, WeatherClient<WeatherStackForecast> weatherStackClient, Averager<Weather> weatherAverager, Mapper mapper) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherStackClient = weatherStackClient;
        this.weatherAverager = weatherAverager;
        this.mapper = mapper;
    }

    public Weather getWeatherByCity(String city) {
        WeatherDto weather1 = getWeatherDtoFromOpenWeatherMap(city);
        WeatherDto weather2 = getWeatherDtoFromWeatherStack(city);


        Weather result = getAverageWeather(weather1,weather2);


        result.setDate(LocalDateTime.now().toLocalDate());
        result.setLocation(getStandardizedLocation());

        return result;
    }

    private WeatherDto getWeatherDtoFromOpenWeatherMap(String city) {
        OpenWeatherMapForecast forecast = openWeatherMapClient.getWeatherByCity(city);

        return mapper.OpenWeatherToWeatherDto(forecast);
    }


    private WeatherDto getWeatherDtoFromWeatherStack(String city) {
        WeatherStackForecast forecast = weatherStackClient.getWeatherByCity(city);

        return mapper.WeatherStackToWeatherDto(forecast);
    }


    private Location getStandardizedLocation(Location...location){

        return null;
    }


    private Weather getAverageWeather(WeatherDto... weathers){
        return weatherAverager.getAverage(weathers);
    }


}
