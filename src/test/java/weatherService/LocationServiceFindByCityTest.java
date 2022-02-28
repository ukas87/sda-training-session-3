package weatherService;

import model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LocationService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceFindByCityTest {

    LocationService locationService;
    Location l1;

    @BeforeEach
    void setUp() {
        locationService = new LocationService();
        l1 = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60L)
                .withLongitude(70L)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();
        locationService.write(l1);

    }

    @AfterEach
    void erase() throws URISyntaxException, IOException {
        Files.write((Paths.get(ClassLoader.getSystemResource("locationData.csv").toURI())),
                ("".getBytes()));
    }


    @Test
    void shouldReturnLocationByCity() {
        Location actual = locationService.findByCity("Krakow");

        assertThat(actual).isEqualTo(l1);
    }

    @Test
    void shouldReturnNullIfNoLocationWithSuchCity(){
        Location actual = locationService.findByCity("Wroclaw");

        assertThat(actual).isNull();
    }
}
