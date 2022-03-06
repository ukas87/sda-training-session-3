package utils;

import model.Location;
import model.Weather;
import model.weatherstack.WeatherStackMapForecast;

public class WeatherStackToWeatherConverter implements ObjectConverter<WeatherStackMapForecast, Weather>{

    private static WeatherStackToWeatherConverter INSTANCE;

    private WeatherStackToWeatherConverter() {
    }

    public static WeatherStackToWeatherConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherStackToWeatherConverter();
        }
        return INSTANCE;
    }


    @Override
    public Weather convert(WeatherStackMapForecast weatherStackMapForecast) {
        Location location = new Location.Builder()
                .withLatitude(weatherStackMapForecast.getLocation().getLat())
                .withLongitude(weatherStackMapForecast.getLocation().getLon())
                .withCityName(weatherStackMapForecast.getLocation().getName())
                .withRegion(weatherStackMapForecast.getLocation().getRegion())
                .withCountryName(weatherStackMapForecast.getLocation().getCountry())
                .build();

        return new Weather.Builder()
                .withTemperature(weatherStackMapForecast.getCurrent().getTemperature())
                .withPressure(weatherStackMapForecast.getCurrent().getPressure())
                .withHumidity(weatherStackMapForecast.getCurrent().getHumidity())
                .withWindSpeed(weatherStackMapForecast.getCurrent().getWind_speed())
                .withLocation(location)
                .build();

    }
}
