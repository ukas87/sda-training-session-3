package dto;

import lombok.Data;
import model.Location;
import model.Weather;

import java.time.LocalDate;

@Data
public class WeatherDto {

    private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Integer windSpeed;
    private LocalDate date;
    private Integer windDegrees;
    private String windDirection;
    private Double latitude;
    private Double longitude;
    private String cityName;
    private String countryName;
    private String region;


    public Weather toWeather(){
        return new Weather.Builder()
                .withTemperature(temperature)
                .withPressure(pressure)
                .withHumidity(humidity)
                .withWindSpeed(windSpeed)
                .withDate(date)
                .withWindDegrees(windDegrees)
                .withWindDirection(windDirection)
                .withLocation(new Location.Builder()
                        .withCityName(cityName)
                        .withCountryName(countryName)
                        .withLongitude(longitude)
                        .withLatitude(latitude)
                        .withRegion(region)
                        .build())
                .build();

    }
}
