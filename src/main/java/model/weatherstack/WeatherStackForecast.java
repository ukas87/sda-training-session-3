package model.weatherstack;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class WeatherStackForecast {

    private Current current;
    private Location location;
    private String name;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public Current getCurrent() {
        return current;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
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
        return "WeatherStackMapForecast{" +
                "current=" + current +
                ", locat=" + location +
                ", name='" + name + '\'' +
                '}';
    }
}
