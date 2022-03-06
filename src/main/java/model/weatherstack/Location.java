package model.weatherstack;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

    private double lon;
    private double lat;
    private String country;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getCountry() {
        return country;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Locat{" +
                "lon=" + lon +
                ", lat=" + lat +
                ", country='" + country + '\'' +
                '}';
    }
}