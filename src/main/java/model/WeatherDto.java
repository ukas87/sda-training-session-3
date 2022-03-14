package model;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with", builderMethodName = "Builder")
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

    @Override
    public String toString() {
        return "WeatherDto{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", date=" + date +
                ", windDegrees=" + windDegrees +
                ", windDirection='" + windDirection + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

}
