package dto;

import model.Location;
import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackForecast;
import utils.FormatConverter;

import java.time.LocalDate;

public class Mapper  {

    public WeatherDto OpenWeatherToWeatherDto(OpenWeatherMapForecast object) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setTemperature(object.getMain().getTemp());
        weatherDto.setPressure(object.getMain().getPressure());
        weatherDto.setHumidity(object.getMain().getHumidity());
        weatherDto.setWindSpeed(object.getWind().getSpeed());
        weatherDto.setDate(LocalDate.now());
        weatherDto.setWindDegrees(object.getWind().getDeg());
        weatherDto.setLatitude(object.getCoord().getLat());
        weatherDto.setLongitude(object.getCoord().getLon());
        weatherDto.setCityName(object.getName());
        weatherDto.setCountryName(object.getSys().getCountry());
        weatherDto.setRegion(null);
        return weatherDto;
    }
    public WeatherDto WeatherStackToWeatherDto(WeatherStackForecast weatherStackForecast) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setTemperature(weatherStackForecast.getCurrent().getTemperature());
        weatherDto.setPressure(weatherStackForecast.getCurrent().getPressure());
        weatherDto.setHumidity(weatherStackForecast.getCurrent().getHumidity());
        weatherDto.setWindSpeed((FormatConverter.getInstance().fromKilometersHourToMeterSeconds(weatherStackForecast.getCurrent().getWindSpeed())));
        weatherDto.setDate(LocalDate.now());
        weatherDto.setWindDegrees(weatherStackForecast.getCurrent().getWindDir());
        weatherDto.setLatitude(weatherStackForecast.getLocation().getLat());
        weatherDto.setLongitude(weatherStackForecast.getLocation().getLon());
        weatherDto.setCityName(weatherStackForecast.getLocation().getName());
        weatherDto.setCountryName(weatherStackForecast.getLocation().getCountry());
        weatherDto.setRegion(weatherStackForecast.getLocation().getRegion());
        return weatherDto;
    }

    public Weather toWeather(WeatherDto dto){
        return new Weather.Builder()
                .withTemperature(dto.getTemperature())
                .withPressure(dto.getPressure())
                .withHumidity(dto.getHumidity())
                .withWindSpeed(dto.getHumidity())
                .withDate(dto.getDate())
                .withWindDegrees(dto.getWindDegrees())
                .withWindDirection(dto.getWindDirection())
                .withLocation(new Location.Builder()
                        .withCityName(dto.getCityName())
                        .withCountryName(dto.getCountryName())
                        .withLongitude(dto.getLongitude())
                        .withLatitude(dto.getLatitude())
                        .withRegion(dto.getRegion())
                        .build())
                .build();
    }

    public WeatherDto toDto(Weather weather){
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setTemperature(weather.getTemperature());
        weatherDto.setPressure(weather.getPressure());
        weatherDto.setHumidity(weather.getHumidity());
        weatherDto.setWindSpeed(weatherDto.getWindSpeed());
        weatherDto.setDate(LocalDate.now());
        weatherDto.setWindDegrees(weather.getWindDegrees());
        weatherDto.setLatitude(weather.getLocation().getLatitude());
        weatherDto.setLongitude(weather.getLocation().getLongitude());
        weatherDto.setCityName(weather.getLocation().getCityName());
        weatherDto.setCountryName(weather.getLocation().getCountryName());
        weatherDto.setRegion(weather.getLocation().getRegion());
        return weatherDto;
    }

}
