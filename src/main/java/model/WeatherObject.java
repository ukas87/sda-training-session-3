package model;

import java.util.UUID;

public class WeatherObject {
    private UUID id;
    private Long latitude;
    private Long longitude;
    private String cityName;
    private String countryName;
    private String region;


    public WeatherObject(UUID id, Long latitude, Long longitude, String cityName, String countryName, String region) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
        this.countryName = countryName;
        this.region = region;
    }

    @Override
    public String toString() {
        return ""+id + "," + latitude + "," + longitude + "," + cityName + "," + countryName + "," + region;
    }
}
