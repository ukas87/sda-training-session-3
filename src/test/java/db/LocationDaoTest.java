package db;
import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationDaoTest {
    LocationDao locationDao = new LocationDao();
    WeatherDao weatherDao = new WeatherDao();
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

        locationDao.save(location);
        locationDao.save(location1);
        locationDao.save(location2);
    }

    @AfterEach
    void clean() {
        weatherDao.deleteAllWeathers();
        locationDao.deleteAllLocations();
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

        locationDao.save(toSave);
        Location expected = locationDao.findByCity("Kabul");

        assertThat(toSave).isEqualTo(expected);
    }

    @Test
    void shouldDeleteLocation() {
        Location before = locationDao.findByCity("New York");

        locationDao.delete(location);
        Location after = locationDao.findByCity("New York");

        assertThat(before).isEqualTo(location);
        assertThat(after).isNull();
    }

    @Test
    void shouldFindLocationByCityAndCountry() {

        Location search = locationDao.findByCityAndCountry("Sydney", "PL");

        assertThat(search).isEqualTo(location2);
    }

    @Test
    void shouldUpdateLocationCountry() {
        Location locationToUpdate = locationDao.findByCity("New York");
        String newCountry = "RU";

        locationToUpdate.setCountryName(newCountry);
        locationDao.update(locationToUpdate);
        Location actual = locationDao.findByCity("New York");

        assertThat(locationToUpdate).isEqualTo(actual);
    }

}
