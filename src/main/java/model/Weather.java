package model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "weathers")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Weather weather = (Weather) o;
        return id != null && Objects.equals(id, weather.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
