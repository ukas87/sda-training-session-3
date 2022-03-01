package utils;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;

public class OpenWeatherMapToWeatherConverter implements ObjectConverter<OpenWeatherMapForecast, Weather> {

    private static OpenWeatherMapToWeatherConverter INSTANCE;

    private OpenWeatherMapToWeatherConverter() {
    }

    public static OpenWeatherMapToWeatherConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OpenWeatherMapToWeatherConverter();
        }
        return INSTANCE;
    }

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
