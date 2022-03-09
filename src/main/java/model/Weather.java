package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "weathers")
@NoArgsConstructor
public class Weather {

    @Id
    @Column(name = "weather_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column
    private Double temperature;

    @Column
    private Integer pressure;

    @Column
    private Integer humidity;

    @Column(name = "wind_speed")
    private Integer windSpeed;


    private LocalDate date;

    @Column(name = "wind_degrees")
    private Integer windDegrees;

    @Column(name = "wind_direction")
    private String windDirection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    public static class Builder {
        private final Weather newWeather;

        public Builder() {
            newWeather = new Weather();
        }

        public Builder withTemperature(Double temperature) {
            newWeather.temperature = temperature;
            return this;
        }

        public Builder withPressure(Integer pressure) {
            newWeather.pressure = pressure;
            return this;
        }

        public Builder withHumidity(Integer humidity) {
            newWeather.humidity = humidity;
            return this;
        }

        public Builder withWindSpeed(Integer windSpeed) {
            newWeather.windSpeed = windSpeed;
            return this;
        }

        public Builder withDate(LocalDate localDateTime) {
            newWeather.date = localDateTime;
            return this;
        }

        public Builder withLocation(Location location) {
            newWeather.location = location;
            return this;
        }
        public Builder withWindDegrees(Integer windDegrees){
            newWeather.windDegrees = windDegrees;
            return this;
        }
        public Builder withWindDirection(String windDirection){
            newWeather.windDirection = windDirection;
            return this;
        }

        public Weather build() {
            return newWeather;
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", date=" + date +
                ", windDegrees=" + windDegrees +
                ", windDirection='" + windDirection + '\'' +
                ", location=" + location.getCityName() +
                '}';
    }
}
