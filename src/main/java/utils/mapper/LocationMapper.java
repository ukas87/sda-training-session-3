package utils.mapper;

import model.Location;
import model.WeatherDto;

public class LocationMapper implements Mapper<Location, WeatherDto> {
    @Override
    public Location toEntity(WeatherDto dto) {
        return Location.Builder()
                .withCityName(dto.getCityName())
                .withCountryName(dto.getCountryName())
                .withLongitude(dto.getLongitude())
                .withLatitude(dto.getLatitude())
                .withRegion(dto.getRegion())
                .build();
    }

    @Override
    public WeatherDto toDto(Location entity) {
        return WeatherDto.Builder()
                .withLatitude(entity.getLatitude())
                .withLongitude(entity.getLongitude())
                .withCityName(entity.getCityName())
                .withCountryName(entity.getCountryName())
                .withRegion(entity.getRegion())
                .build();
    }
}
