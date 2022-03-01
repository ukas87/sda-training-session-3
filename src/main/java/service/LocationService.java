package service;
import model.Location;
import parser.Parser;
import writer.Writer;
import java.util.List;

public class LocationService {

    private final Parser<Location> locationParser;
    private final Writer<Location> locationWriter;

    public LocationService(Parser<Location> locationParser, Writer<Location> locationWriter) {
        this.locationParser = locationParser;
        this.locationWriter = locationWriter;
    }

    public List<Location> getLocationObjectsFromFile() {
        return locationParser.getObjectFromFile();
    }

    public Location findByCity(String city) {
        return getLocationObjectsFromFile().stream()
                .filter(location -> location.getCityName().equals(city))
                .findFirst()
                .orElse(null);
    }

    public void displayAllLocations() {
        getLocationObjectsFromFile().forEach(System.out::println);
    }

    public void write(Location location) {
        locationWriter.write(location);
    }

    public void eraseAllData() {
        locationWriter.eraseAllData();
    }
}
