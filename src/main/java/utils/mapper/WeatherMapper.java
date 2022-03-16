package utils.mapper;

import model.Location;
import model.Weather;
import model.WeatherDto;

public class WeatherMapper implements Mapper<Weather, WeatherDto> {

    @Override
    public WeatherDto toDto(Weather entity) {
        return WeatherDto.Builder()
                .withTemperature(entity.getTemperature())
                .withPressure(entity.getPressure())
                .withHumidity(entity.getHumidity())
                .withWindSpeed(entity.getWindSpeed())
                .withDate(entity.getDate())
                .withWindDegrees(entity.getWindDegrees())
                .withLatitude(entity.getLocation().getLatitude())
                .withLongitude(entity.getLocation().getLongitude())
                .withCityName(entity.getLocation().getCityName())
                .withCountryName(entity.getLocation().getCountryName())
                .withRegion(entity.getLocation().getRegion())
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
