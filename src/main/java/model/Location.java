package model;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Location {

    @Id
    private UUID id;
    private Double latitude;
    private Double longitude;
    private String cityName;
    private String countryName;
    private String region;

    public Location() {
    }

    public String getCityName() {
        return cityName;
    }

    public static class Builder {
        private final Location newLocation;

        public Builder() {
            newLocation = new Location();
        }

        public Builder withId(UUID id) {
            newLocation.id = id;
            return this;
        }

        public Builder withLatitude(Double latitude) {
            newLocation.latitude = latitude;
            return this;
        }

        public Builder withLongitude(Double longitude) {
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

    public UUID getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id.equals(location.id) && latitude.equals(location.latitude) && longitude.equals(location.longitude) && cityName.equals(location.cityName) && countryName.equals(location.countryName) && region.equals(location.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, cityName, countryName, region);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public String toWriteFormat(){
        return "" + id + "," + latitude + "," + longitude + "," + cityName + "," + countryName + "," + region;
    }
}
