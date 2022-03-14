package db;
import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import model.Weather;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WeatherDaoCrudTest {
    WeatherDao weatherDao = new WeatherDao();
    Weather weather;
    Weather weather2;

    @AfterAll
    public static void clean(){
        WeatherDao weatherDao = new WeatherDao();
        weatherDao.deleteAllWeathers();
        LocationDao locationDao = new LocationDao();
        locationDao.deleteAllLocations();
    }

    @Test
    @Order(1)
    void shouldSaveWeather() {
        weather = Weather.Builder()
                .withDate(LocalDate.now())
                .withHumidity(33)
                .withPressure(587)
                .withTemperature(4.0)
                .withWindDegrees(1)
                .withWindDirection("S")
                .withWindSpeed(33)
                .withLocation(Location.Builder()
                        .build())
                .build();

        weatherDao.save(weather);

        Weather expected = weatherDao.findById(1);

        assertThat(expected).isEqualTo(weather);
    }


    @Test
    @Order(2)
    void shouldUpdateWeather() {
        Weather weatherToUpdate = weatherDao.findById(1);
        Double newTemp = -66.6;

        weatherToUpdate.setTemperature(newTemp);
        weatherDao.update(weatherToUpdate);
        Weather actualWeather = weatherDao.findById(1);

        assertThat(weatherToUpdate).isEqualTo(actualWeather);
    }

    @Test
    @Order(3)
    void shouldDeleteWeather() {
        weather2 = Weather.Builder()
                .withDate(LocalDate.now())
                .withHumidity(66)
                .withPressure(522)
                .withTemperature(41.0)
                .withWindDegrees(55)
                .withWindDirection("W")
                .withWindSpeed(33)
                .withLocation(Location.Builder()
                        .build())
                .build();
        weatherDao.save(weather2);
        Weather weatherBefore = weatherDao.findById(2);

        weatherDao.delete(weather2);
        Weather after = weatherDao.findById(2);

        assertThat(weatherBefore).isEqualTo(weather2);
        assertThat(after).isNull();
    }
}