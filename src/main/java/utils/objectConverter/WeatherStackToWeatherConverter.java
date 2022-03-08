package utils.objectConverter;

import model.Location;
import model.Weather;
import model.weatherstack.WeatherStackForecast;
import utils.FormatConverter;

public class WeatherStackToWeatherConverter implements ObjectConverter<WeatherStackForecast, Weather>{

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
    public Weather convert(WeatherStackForecast weatherStackForecast) {
        Location location = new Location.Builder()
                //.withLatitude(weatherStackForecast.getLocation().getLat())
                .withLatitude(FormatConverter.getInstance().latitudeFormatter(weatherStackForecast.getLocation().getLat()))
               // .withLongitude(weatherStackForecast.getLocation().getLon())
                .withLongitude(FormatConverter.getInstance().longitudeFormatter(weatherStackForecast.getLocation().getLon()))
                .withCityName(weatherStackForecast.getLocation().getName())
                .withRegion(weatherStackForecast.getLocation().getRegion())
                .withCountryName(weatherStackForecast.getLocation().getCountry())
                //.withCountryName(FormatConverter.getInstance().countryNameFormatter(weatherStackForecast.getLocation().getCountry()))
                .build();

        return new Weather.Builder()
                .withTemperature(weatherStackForecast.getCurrent().getTemperature())
                .withPressure(weatherStackForecast.getCurrent().getPressure())
                .withHumidity(weatherStackForecast.getCurrent().getHumidity())
                .withWindSpeed((FormatConverter.getInstance().fromKilometersHourToMeterSeconds(weatherStackForecast.getCurrent().getWindSpeed())))
                .withWindDegrees(weatherStackForecast.getCurrent().getWindDir())
                .withLocation(location)
                .build();

    }
}
