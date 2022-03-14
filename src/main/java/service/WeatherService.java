package service;

import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import utils.mapper.WeatherMapper;
import model.WeatherDto;
import model.Weather;
import utils.averager.Averager;

import java.time.LocalDate;

public class WeatherService {

    final WeatherClient openWeatherMapClient;
    final WeatherClient weatherStackClient;
    final Averager<WeatherDto> weatherAverager;
    final WeatherMapper weatherMapper;
    final LocationDao locationDao;
    final WeatherDao weatherDao;


    public WeatherService(WeatherClient openWeatherMapClient, WeatherClient weatherStackClient, Averager<WeatherDto> weatherAverager, WeatherMapper weatherMapper, LocationDao locationDao, WeatherDao weatherDao) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherStackClient = weatherStackClient;
        this.weatherAverager = weatherAverager;
        this.weatherMapper = weatherMapper;
        this.locationDao = locationDao;
        this.weatherDao = weatherDao;
    }

    public WeatherDto getAverageWeatherDtoByCity(String city) {
        WeatherDto weather1 = getWeatherDtoFromOpenWeatherMap(city);
        WeatherDto weather2 = getWeatherDtoFromWeatherStack(city);

        WeatherDto averageWeatherDto = getAverageWeatherDto(weather1, weather2);
        averageWeatherDto.setDate(LocalDate.now());

        Weather weatherToSave = weatherMapper.toEntity(averageWeatherDto);


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

        return openWeatherMapClient.getWeatherByCity(city);
    }


    private WeatherDto getWeatherDtoFromWeatherStack(String city) {

        return openWeatherMapClient.getWeatherByCity(city);
    }

    private WeatherDto getAverageWeatherDto(WeatherDto... weathers) {
        return weatherAverager.getAverage(weathers);
    }


}
