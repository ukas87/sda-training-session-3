package utils.objectConverter;
import model.Location;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import utils.FormatConverter;


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
               //.withLatitude(openWeatherMapForecast.getCoord().getLat())
                .withLatitude(FormatConverter.getInstance().latitudeFormatter(openWeatherMapForecast.getCoord().getLat()))
               //.withLongitude(openWeatherMapForecast.getCoord().getLon())
                .withLongitude(FormatConverter.getInstance().longitudeFormatter(openWeatherMapForecast.getCoord().getLon()))
               //.withCityName(openWeatherMapForecast.getName())
                .withCityName(FormatConverter.getInstance().cityNameFormatters(openWeatherMapForecast.getName()))
                //.withCountryName(openWeatherMapForecast.getSys().getCountry())
                .withCountryName(FormatConverter.getInstance().countryNameFormatter(openWeatherMapForecast.getSys().getCountry()))
                .build();


        return new Weather.Builder()
                //.withTemperature(openWeatherMapForecast.getMain().getTemp())
                .withTemperature(FormatConverter.getInstance().temperatureFormatter(openWeatherMapForecast.getMain().getTemp()))
                .withPressure(openWeatherMapForecast.getMain().getPressure())
                .withHumidity(openWeatherMapForecast.getMain().getHumidity())
                .withWindSpeed(openWeatherMapForecast.getWind().getSpeed())
                .withWindDegrees(openWeatherMapForecast.getWind().getDeg())
                .withLocation(location)
                .build();
    }
}
