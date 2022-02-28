package model;

import java.util.UUID;

public class Location {
    private UUID id;
    private Long latitude;
    private Long longitude;
    private String cityName;
    private String countryName;
    private String region;

    public static class Builder {
        private final Location newLocation;

        public Builder() {
            newLocation = new Location();
        }

        public Builder withId(UUID id) {
            newLocation.id = id;
            return this;
        }

        public Builder withLatitude(Long latitude) {
            newLocation.latitude = latitude;
            return this;
        }

        public Builder withLongitude(Long longitude) {
            newLocation.longitude = longitude ;
            return this;
        }

        public Builder withCityName(String cityName) {
            newLocation.cityName = cityName;
            return this;
        }

        public Builder withCountryName(String countryName) {
            newLocation.countryName = countryName;
            return this;
        }

        public Builder withRegion(String region) {
            newLocation.region = region;
            return this;
        }

        public Location build(){
            return newLocation;
        }

    }

    @Override
    public String toString() {
        return "" + id + "," + latitude + "," + longitude + "," + cityName + "," + countryName + "," + region;
    }
}
