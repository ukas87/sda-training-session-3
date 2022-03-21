package service;

import dao.LocationDao;
import dao.WeatherDao;
import lombok.extern.log4j.Log4j2;
import model.Location;
import utils.mapper.LocationMapper;
import utils.mapper.WeatherMapper;
import model.WeatherDto;
import model.Weather;
import utils.averager.Averager;

import java.time.LocalDate;
import java.util.List;

@Log4j2
public class WeatherService {

    final WeatherClient openWeatherMapClient;
    final WeatherClient weatherStackClient;
    final Averager<WeatherDto> weatherAverager;
    final WeatherMapper weatherMapper;
    final LocationMapper locationMapper;
    final LocationDao locationDao;
    final WeatherDao weatherDao;


    public WeatherService(OpenWeatherMapClient openWeatherMapClient, WeatherStackClient weatherStackClient, Averager<WeatherDto> weatherAverager, WeatherMapper weatherMapper, LocationMapper locationMapper, LocationDao locationDao, WeatherDao weatherDao) {
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherStackClient = weatherStackClient;
        this.weatherAverager = weatherAverager;
        this.weatherMapper = weatherMapper;
        this.locationMapper = locationMapper;
        this.locationDao = locationDao;
        this.weatherDao = weatherDao;
    }


    public WeatherDto getAverageWeatherDtoByCityNameFromBase(String city) {
        Double lat = null;
        Double lon = null;
        try {
            Location location = locationDao.findByCity(city);
            lat = location.getLatitude();
            lon = location.getLongitude();
        } catch (Exception e) {
            log.error(e);
        }
        WeatherDto average = getAverageWeatherDtoByCoordinates(lat, lon);
        average.setCityName(city);
        return average;
    }

    public WeatherDto getAverageWeatherDtoByCoordinates(Double lat, Double lon) {
        WeatherDto weather1 = openWeatherMapClient.getWeatherByCoordinates(lat, lon);
        WeatherDto weather2 = weatherStackClient.getWeatherByCoordinates(lat, lon);
        List<WeatherDto> toAverage = List.of(weather1, weather2);

        WeatherDto weatherDto = getAverageWeatherDto(toAverage);
        weatherDto.setDate(LocalDate.now());
        return weatherDto;
    }

    public void saveWeather(WeatherDto weatherDto) {
        Weather weather = weatherMapper.toEntity(weatherDto);
        weather.setLocation(locationDao.findByCity(weatherDto.getCityName()));
        weatherDao.save(weather);

    }

    public void displayHistoricalAverageByDateAndCity(LocalDate date, String city){
        List<WeatherDto> dtos = getAllWeathersByDate(date, city).stream()
                .map(weatherMapper::toDto)
                .toList();
        displayWeather(weatherAverager.getAverage(dtos));

    }

    public WeatherDto getAverageWeatherDto(List<WeatherDto> weathers) {
        return weatherAverager.getAverage(weathers);
    }

    public List<Weather> getAllWeathersByDate(LocalDate date, String cityName) {
        return weatherDao.getWeatherByDateAndCity(date, cityName);
    }

    public void displayWeathers(List<Weather> weathers) {
        List<WeatherDto> listToDisplay = weathers.stream()
                .map(weatherMapper::toDto)
                .toList();

        for (WeatherDto weather : listToDisplay) {
            displayWeather(weather);
            System.out.println();
        }
    }

    public void displayWeather(WeatherDto weatherDto) {
        System.out.println("City: " + weatherDto.getCityName() +
                "\nCountry: " + weatherDto.getCountryName() +
                "\nRegion: " + weatherDto.getRegion() +
                "\nDate: " + weatherDto.getDate() +
                "\nTemperature: " + weatherDto.getTemperature() + " C" +
                "\nPressure: " + weatherDto.getPressure() + " Pa" +
                "\nHumidity: " + weatherDto.getHumidity() + " %" +
                "\nWind direction: " + weatherDto.getWindDirection() +
                "\nWind speed: " + weatherDto.getWindSpeed() + " km/hour");
    }

}
