package utils;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;


public class WeatherMapToStandardConverter implements ObjectConverter<OpenWeatherMapForecast, Weather> {
    @Override
    public Weather convert(OpenWeatherMapForecast object) {
        return null;
    }
}
