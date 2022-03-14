package db;
import dao.LocationDao;
import model.Location;
import model.Weather;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationDaoTest {
    LocationDao dao = new LocationDao();
    Location location;
    Location location1;
    Location location2;

    @BeforeEach
    void setUp() {
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

        location2 = Location.Builder()
                .withCityName("Sydney")
                .withCountryName("PL")
                .withLatitude(666.132)
                .withLongitude(11.321)
                .withRegion("FarFromOcean")
                .build();

        dao.save(location);
        dao.save(location1);
        dao.save(location2);
    }

    @AfterEach
    void clean() {
        dao.deleteAllLocations();
    }

    @Test
    void shouldSaveLocation() {
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
    void shouldDeleteLocation() {
        Location before = dao.findByCity("New York");

        dao.delete(location);
        Location after = dao.findByCity("New York");

        assertThat(before).isEqualTo(location);
        assertThat(after).isNull();
    }

    @Test
    void shouldFindLocationByCityAndCountry() {

        Location search = dao.findByCityAndCountry("Sydney", "PL");

        assertThat(search).isEqualTo(location2);
    }

    @Test
    void shouldUpdateLocationCountry() {
        Location locationToUpdate = dao.findByCity("New York");
        String newCountry = "RU";

        locationToUpdate.setCountryName(newCountry);
        dao.update(locationToUpdate);
        Location actual = dao.findByCity("New York");

        assertThat(locationToUpdate).isEqualTo(actual);
    }

    @Test
    void shouldAddWeatherWithExistingLocation() {
        Weather toSave = Weather.Builder()
                .withLocation(location2)
                .withTemperature(42.00)
                .build();
        int locations = dao.findAll().size();
        System.out.println(locations);

        dao.saveWeather(toSave);

        assertThat(locations).isEqualTo(3);
    }
}
