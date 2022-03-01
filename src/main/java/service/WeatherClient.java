package service;

public interface WeatherClient<T> {
    T getWeatherByCity(String city);


}
