package db;

import dao.LocationDao;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationDaoTest {
    LocationDao dao = new LocationDao();
    Location location;
    Location location1;

    @BeforeEach
    void setUp(){
        location = Location.Builder()
                .withCityName("New York")
                .withCountryName("US")
                .withLatitude(123.132)
                .withLongitude(321.321)
                .withRegion("Unknown")
                .build();

        location1 = Location.Builder()
                .withCityName("Sydney")
                .withCountryName("AU")
                .withLatitude(999.132)
                .withLongitude(222.321)
                .withRegion("NearTheOcean")
                .build();
        dao.save(location);
        dao.save(location1);
    }

    @Test
    void shouldSaveLocation(){
        Location toSave = Location.Builder()
                .withCityName("Kabul")
                .withCountryName("AF")
                .withLatitude(12.132)
                .withLongitude(66.321)
                .withRegion("Mountains")
                .build();

        dao.save(toSave);
        Location expected = dao.findByCity("Kabul");

        assertThat(toSave).isEqualTo(expected);
    }

    @Test
    void shouldDeleteLocation(){
        Location before = dao.findByCity("Sydney");

        dao.delete(location1);
        Location after = dao.findByCity("Sydney");

        assertThat(before).isEqualTo(location1);
        assertThat(after).isNull();

    }
}
