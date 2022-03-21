package db;
import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import model.Weather;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherDaoTest {
    WeatherDao weatherDao = new WeatherDao();

    @BeforeAll
    static void setUp() {
        LocationDao locationDao = new LocationDao();
        WeatherDao weatherDao = new WeatherDao();

        Location location1 = Location.Builder()
                .withCityName("New York")
                .withCountryName("US")
                .withRegion("New York")
                .withLatitude(23.123)
                .withLongitude(21.321)
                .build();
        locationDao.save(location1);

        Location location2 = Location.Builder()
                .withCityName("Krakow")
                .withCountryName("PL")
                .withRegion("Lesser Poland")
                .withLatitude(11.111)
                .withLongitude(22.222)
                .build();
        locationDao.save(location2);

        weatherDao.save(Weather.Builder()
                .withTemperature(17.0)
                .withPressure(1000)
                .withHumidity(12)
                .withWindDegrees(4)
                .withLocation(location1)
                .build());

        weatherDao.save(Weather.Builder()
                .withTemperature(-10.0)
                .withPressure(1111)
                .withHumidity(11)
                .withWindDegrees(3)
                .withLocation(location1)
                .build());

        weatherDao.save(Weather.Builder()
                .withTemperature(12.0)
                .withPressure(999)
                .withHumidity(82)
                .withWindDegrees(14)
                .withLocation(location2)
                .build());
    }

    @AfterAll
    static void clean(){
        WeatherDao weatherDao = new WeatherDao();
        weatherDao.deleteAllWeathers();
        LocationDao locationDao = new LocationDao();
        locationDao.deleteAllLocations();
    }

    @Test
    void shouldReturn3Weathers(){

        List<Weather> weathers = weatherDao.getAllWeathers();

        assertThat(weathers.size()).isEqualTo(3);
    }

    @Test
    void shouldReturn2WeathersWhenFindByCity(){

        List<Weather> weathers = weatherDao.findByCity("New York");

        assertThat(weathers.size()).isEqualTo(2);
    }

    @Test
    void  shouldReturnEmptyListWhenCityNotExist(){

        List<Weather> weathers = weatherDao.findByCity("Kabul");

        assertThat(weathers.size()).isEqualTo(0);
    }

}
