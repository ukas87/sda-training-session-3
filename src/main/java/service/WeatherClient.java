package service;

import model.WeatherDto;

public interface WeatherClient {
    WeatherDto getWeatherByCity(String city);


}
