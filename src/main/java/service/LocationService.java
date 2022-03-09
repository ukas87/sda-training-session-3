package service;

import dao.LocationDao;
import model.Location;

public class LocationService {

    LocationDao locationDao;


    public void add(Location location){
        locationDao.save(location);
    }

    public void delete(Location location){
        locationDao.delete(location);
    }

    public Location findByCity(String city){
        return locationDao.findByCity(city);
    }


}
