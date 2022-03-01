package utils;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;


public  class OpenWeatherMapToWeatherConverter implements ObjectConverter<OpenWeatherMapForecast, Weather> {
    @Override
    public Weather convert(OpenWeatherMapForecast openWeatherMapForecast) {

        return new Weather.Builder()
                .withTemperature(openWeatherMapForecast.getMain().getTemp())
                .withPressure(openWeatherMapForecast.getMain().getPressure())
                .withHumidity(openWeatherMapForecast.getMain().getHumidity())
                .withWindSpeed(openWeatherMapForecast.getWind().getSpeed())
                .build();
    }
}
