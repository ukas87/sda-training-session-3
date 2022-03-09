package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "weathers")
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with",builderMethodName = "Builder")
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
