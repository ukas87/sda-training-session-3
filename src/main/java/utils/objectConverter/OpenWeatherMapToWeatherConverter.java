package utils.objectConverter;
import model.Location;
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

        Location location = new Location.Builder()
                .withLatitude(openWeatherMapForecast.getCoord().getLat())
                .withLongitude(openWeatherMapForecast.getCoord().getLon())
                .withCityName(openWeatherMapForecast.getName())
                .withCountryName(openWeatherMapForecast.getSys().getCountry())
                .build();


        return new Weather.Builder()
                .withTemperature(openWeatherMapForecast.getMain().getTemp())
                .withPressure(openWeatherMapForecast.getMain().getPressure())
                .withHumidity(openWeatherMapForecast.getMain().getHumidity())
                .withWindSpeed(openWeatherMapForecast.getWind().getSpeed())
                .withWindDegrees(openWeatherMapForecast.getWind().getDeg())
                .withLocation(location)
                .build();
    }
}
