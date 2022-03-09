package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "locations")
@NoArgsConstructor
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




}
