package model;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "locations")
@AllArgsConstructor
@Builder(setterPrefix = "with",builderMethodName = "Builder")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region")
    private String region;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private List<Weather> weathers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return id != null && Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
