package model.dao;

import model.Location;
import model.Weather;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDaoTest {
    WeatherDao weatherDao = new WeatherDao();
    LocationDao locationDao = new LocationDao();
    Weather weather;
    Weather weather1;

    @BeforeEach
    void setUp(){
        weather = Weather.Builder()
                .withDate(LocalDate.now())
                .withHumidity(11)
                .withPressure(111)
                .withTemperature(4.0)
                .withWindDegrees(1)
                .withWindDirection("S")
                .withWindSpeed(33)
                .withLocation(new Location())
                .build();

        weather1 = Weather.Builder()
                .withDate(LocalDate.now())
                .withHumidity(22)
                .withPressure(567)
                .withTemperature(4.0)
                .withWindDegrees(1)
                .withWindDirection("S")
                .withWindSpeed(33)
                .withLocation(new Location())
                .build();
        weatherDao.save(weather);
        weatherDao.save(weather1);
    }

//    @AfterEach
//    void clean() {
//        weatherDao.deleteAllWeathers();
//    }

    @Test
    void shouldSaveWeather() {
        Weather toSave = Weather.Builder()
                .withDate(LocalDate.now())
                .withHumidity(33)
                .withPressure(587)
                .withTemperature(4.0)
                .withWindDegrees(1)
                .withWindDirection("S")
                .withWindSpeed(33)
                .withLocation(new Location(1,22.22,33.33,"KRK", "PL", "Mazowieckie", null))
                .build();

        weatherDao.save(toSave);

        Weather expected = weatherDao.findById(3);

        assertThat(expected).isEqualTo(toSave);
    }

    @Test
    void shouldDeleteWeather(){
        Weather weatherBefore = weatherDao.findById(3);

        weatherDao.delete(weather);
        Weather after = weatherDao.findById(3);

        //assertThat(weatherBefore).isEqualTo(weather);
        assertThat(after).isNull();
    }


    @Test
    void shouldUpdateWeather(){
        Weather weatherToUpdate = weatherDao.findById(2);
        Double newTemp = -66.6;

        weatherToUpdate.setTemperature(newTemp);
        weatherDao.update(weatherToUpdate);
        Weather actualWeather = weatherDao.findById(2);

        assertThat(weatherToUpdate).isEqualTo(actualWeather);

    }
}