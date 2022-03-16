package service;

import model.WeatherDto;

public interface WeatherClient {
    WeatherDto getWeatherByCity(String city);
    WeatherDto getWeatherByCoordinates(Double lat, Double lon);


}
