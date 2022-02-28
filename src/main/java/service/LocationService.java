package service;
import model.Location;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationService {

    private static final String PATH = "locationData.csv";

    //creating objects from parsed with 'getLinesFromFile()' method data
    public List<Location> getWeatherObjectsFromFile() {
        List<String[]> lines = getLinesFromFile();

        return lines.stream()
                .map(line -> new Location.Builder()
                        .withId(UUID.fromString(line[0]))
                        .withLatitude(Long.parseLong(line[1]))
                        .withLongitude(Long.parseLong(line[2]))
                        .withCityName(line[3])
                        .withCountryName(line[4])
                        .withRegion(line[5])
                        .build())
                .collect(Collectors.toList());

    }

    public Location findByCity(String city){
        return getWeatherObjectsFromFile().stream()
                .filter(location -> location.getCityName().equals(city))
                .findFirst()
                .orElse(null);
    }

    //printing all locations from file
    public void displayAllLocations(){
        for (Location location: getWeatherObjectsFromFile()){
            System.out.println(location);
        }
    }

    //parsing data from csv file
    private List<String[]> getLinesFromFile() {
        List<String[]> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(PATH).toURI()))) {
            lines = stream
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to read the file.");
        }
        return lines;
    }

    //appending new locations to existing file
    public void write(Location location) {
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    (location.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void eraseAllData(){
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    ("".getBytes()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
