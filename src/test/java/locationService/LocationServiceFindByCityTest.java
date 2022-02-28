package locationService;

import model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.CsvLocationParser;
import parser.Parser;
import service.LocationService;
import writer.CsvLocationWriter;
import writer.Writer;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceFindByCityTest {

    private static final String PATH = "locationData.csv";
    LocationService locationService;
    Parser<Location> locationParser = new CsvLocationParser(PATH);
    Writer<Location> locationWriter = new CsvLocationWriter(PATH);
    Location location1;

    @BeforeEach
    void setUp() {
        locationService = new LocationService(locationParser, locationWriter);
        location1 = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60L)
                .withLongitude(70L)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();
        locationService.write(location1);
    }

    @AfterEach
    void erase() {
        locationService.eraseAllData();
    }

    @Test
    void shouldReturnLocationByCity() {
        //when
        Location actual = locationService.findByCity("Krakow");

        //then
        assertThat(actual).isEqualTo(location1);
    }

    @Test
    void shouldReturnNullIfNoLocationWithSuchCity() {
        //when
        Location actual = locationService.findByCity("Wroclaw");

        //then
        assertThat(actual).isNull();
    }
}
