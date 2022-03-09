package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "locations")
@NoArgsConstructor
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

    public static class Builder {
        private final Location newLocation;

        public Builder() {
            newLocation = new Location();
        }

        public Builder withId(Integer id) {
            newLocation.id = id;
            return this;
        }

        public Builder withLatitude(Double latitude) {
            newLocation.latitude = latitude;
            return this;
        }

        public Builder withLongitude(Double longitude) {
            newLocation.longitude = longitude ;
            return this;
        }

        public Builder withCityName(String cityName) {
            newLocation.cityName = cityName;
            return this;
        }

        public Builder withCountryName(String countryName) {
            newLocation.countryName = countryName;
            return this;
        }

        public Builder withRegion(String region) {
            newLocation.region = region;
            return this;
        }

        public Location build(){
            return newLocation;
        }

    }
}
