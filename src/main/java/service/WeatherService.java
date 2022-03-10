package service;

import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import utils.mapper.Mapper;
import model.WeatherDto;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackForecast;
import utils.averager.Averager;

import java.time.LocalDate;

public class WeatherService {

    final WeatherClient<OpenWeatherMapForecast> openWeatherMapClient;
    final WeatherClient<WeatherStackForecast> weatherStackClient;
    final Averager<WeatherDto> weatherAverager;
    final Mapper mapper;
    final LocationDao locationDao;
    final WeatherDao weatherDao;


    public WeatherService(WeatherClient<OpenWeatherMapForecast> openWeatherMapClient, WeatherClient<WeatherStackForecast> weatherStackClient, Averager<WeatherDto> weatherAverager, Mapper mapper, LocationDao locationDao, WeatherDao weatherDao) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherStackClient = weatherStackClient;
        this.weatherAverager = weatherAverager;
        this.mapper = mapper;
        this.locationDao = locationDao;
        this.weatherDao = weatherDao;
    }

    public WeatherDto getAverageWeatherDtoByCity(String city) {
        WeatherDto weather1 = getWeatherDtoFromOpenWeatherMap(city);
        WeatherDto weather2 = getWeatherDtoFromWeatherStack(city);

        WeatherDto averageWeatherDto = getAverageWeatherDto(weather1, weather2);
        averageWeatherDto.setDate(LocalDate.now());

        Weather weatherToSave = mapper.dtoToWeather(averageWeatherDto);


        return averageWeatherDto;
    }

    public void saveWeather(Weather weather){
        Location location = locationDao.findByCityAndCountry(weather.getLocation().getCityName(), weather.getLocation().getCountryName());
        if(location != null){
            weather.setLocation(location);
            weatherDao.save(weather);
        }else{
            weatherDao.save(weather);
        }

    }

    private WeatherDto getWeatherDtoFromOpenWeatherMap(String city) {
        OpenWeatherMapForecast forecast = openWeatherMapClient.getWeatherByCity(city);

        return mapper.OpenWeatherToWeatherDto(forecast);
    }


    private WeatherDto getWeatherDtoFromWeatherStack(String city) {
        WeatherStackForecast forecast = weatherStackClient.getWeatherByCity(city);
        return mapper.WeatherStackToWeatherDto(forecast);
    }

    private WeatherDto getAverageWeatherDto(WeatherDto... weathers) {
        return weatherAverager.getAverage(weathers);
    }


}
