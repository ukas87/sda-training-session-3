package utils.mapper;

import model.Location;
import model.Weather;
import model.WeatherDto;

public class WeatherMapper implements Mapper<Weather, WeatherDto> {

    @Override
    public WeatherDto toDto(Weather weather) {
        return WeatherDto.Builder()
                .withTemperature(weather.getTemperature())
                .withPressure(weather.getPressure())
                .withHumidity(weather.getHumidity())
                .withWindSpeed(weather.getWindSpeed())
                .withWindDegrees(weather.getWindDegrees())
                .withLatitude(weather.getLocation().getLatitude())
                .withLongitude(weather.getLocation().getLongitude())
                .withCityName(weather.getLocation().getCityName())
                .withCountryName(weather.getLocation().getCountryName())
                .withRegion(weather.getLocation().getRegion())
                .build();
    }

    @Override
    public Weather toEntity(WeatherDto dto) {
        return Weather.Builder()
                .withTemperature(dto.getTemperature())
                .withPressure(dto.getPressure())
                .withHumidity(dto.getHumidity())
                .withWindSpeed(dto.getHumidity())
                .withDate(dto.getDate())
                .withWindDegrees(dto.getWindDegrees())
                .withWindDirection(dto.getWindDirection())
                .withLocation(Location.Builder()
                        .withCityName(dto.getCityName())
                        .withCountryName(dto.getCountryName())
                        .withLongitude(dto.getLongitude())
                        .withLatitude(dto.getLatitude())
                        .withRegion(dto.getRegion())
                        .build())
                .build();
    }

}
