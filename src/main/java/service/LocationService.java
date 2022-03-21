package service;
import dao.LocationDao;
import model.Location;
import model.WeatherDto;
import utils.mapper.Mapper;
import java.util.List;

public class LocationService {

    private final LocationDao locationDao;
    private final Mapper<Location, WeatherDto> locationMapper;

    public LocationService(LocationDao locationDao, Mapper<Location, WeatherDto> locationMapper) {
        this.locationDao = locationDao;
        this.locationMapper = locationMapper;
    }

    public void add(Location location) {
        locationDao.save(location);
    }

    public void delete(Location location) {
        locationDao.delete(location);
    }

    public Location findByCity(String city) {
        return locationDao.findByCity(city);
    }

    public void update(Location location) {
        locationDao.update(location);
    }

    public List<Location> getAllLocations() {
        return locationDao.findAll();
    }

    public void displayAllLocations() {
        List<WeatherDto> listToDisplay = getAllLocations().stream()
                .map(locationMapper::toDto)
                .toList();

        for (WeatherDto location : listToDisplay) {
            displayLocation(location);
        }
    }

    public void deleteLocationWithWeatherRelatedByCityName(String cityName){
        locationDao.deleteLocationWithRelatedWeatherByCityName(cityName);
    }

    public void displayLocation(WeatherDto weatherDto) {
        System.out.println("City: " + weatherDto.getCityName() + " | " +
                "Country: " + weatherDto.getCountryName() + " | " +
                "Region: " + weatherDto.getRegion() + " | " +
                "Coordinates: " + weatherDto.getLatitude() + ", " + weatherDto.getLongitude());
    }
}
