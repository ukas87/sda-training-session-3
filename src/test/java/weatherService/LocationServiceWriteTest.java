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
import java.util.stream.Collectors;


import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceWriteTest {

    LocationService locationService;


    @BeforeEach
    void setUp() {
        locationService = new LocationService();
    }

    @AfterEach
    void erase() throws URISyntaxException, IOException {
        Files.write((Paths.get(ClassLoader.getSystemResource("locationData.csv").toURI())),
                ("".getBytes()));
    }

    @Test
    void shouldWriteLocationToFile() throws URISyntaxException, IOException {
        Location l1 = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60L)
                .withLongitude(70L)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();

        locationService.write(l1);
        String actual = Files.lines(Paths.get(ClassLoader.getSystemResource("locationData.csv").toURI()))
                .findFirst().orElse(null);

        assertThat(actual).isEqualTo(l1.toString());
    }

    @Test
    void shouldAppendLocation() throws URISyntaxException, IOException {
        Location l1 = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60L)
                .withLongitude(70L)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();
        Location l2 = new Location.Builder()
                .withId(UUID.fromString("6f87f5fe-bc8b-4e8b-b65c-ff83cf370318"))
                .withLatitude(99L)
                .withLongitude(66L)
                .withCityName("London")
                .withCountryName("UnitedKingdoms")
                .withRegion("UnitedKingdoms")
                .build();

        locationService.write(l1);
        locationService.write(l2);
        String actual = Files.lines(Paths.get(ClassLoader.getSystemResource("locationData.csv").toURI()))
                .collect(Collectors.joining());


        assertThat(actual).isEqualTo(l1.toString().concat(l2.toString()));
    }

}
