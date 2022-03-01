package locationService;
import model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.parser.CsvLocationParser;
import utils.parser.Parser;
import service.LocationService;
import utils.writer.CsvLocationWriter;
import utils.writer.Writer;
import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceWriteTest {
    private static final String PATH = "locationData.csv";
    LocationService locationService;
    Parser<Location> locationParser = new CsvLocationParser(PATH);
    Writer<Location> locationWriter = new CsvLocationWriter(PATH);

    @BeforeEach
    void setUp() {
        locationService = new LocationService(locationParser,locationWriter);
    }

    @AfterEach
    void erase(){
        locationService.eraseAllData();
    }

    @Test
    void shouldWriteLocationToFile() {
        //given
        Location expected = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60D)
                .withLongitude(70D)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();

        //when
        locationService.write(expected);
        Location actual = locationService.getLocationObjectsFromFile().get(0);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldAppendLocation() {
        //given
        Location location1 = new Location.Builder()
                .withId(UUID.fromString("5616a660-01f2-4b5b-8874-f56cdb037e03"))
                .withLatitude(60D)
                .withLongitude(70D)
                .withCityName("Krakow")
                .withCountryName("Poland")
                .withRegion("LesserPoland")
                .build();

        Location location2 = new Location.Builder()
                .withId(UUID.fromString("6f87f5fe-bc8b-4e8b-b65c-ff83cf370318"))
                .withLatitude(99D)
                .withLongitude(66D)
                .withCityName("London")
                .withCountryName("UnitedKingdoms")
                .withRegion("UnitedKingdoms")
                .build();

        //when
        locationService.write(location1);
        locationService.write(location2);
        List<Location> expected = List.of(location1,location2);
        List<Location> actual = locationService.getLocationObjectsFromFile();

        //then
        assertThat(actual).isEqualTo(expected);
    }

}
