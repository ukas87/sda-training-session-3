package model.weatherstack;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Current {

    private double temperature;

    @JsonProperty("wind_speed")
    private int windSpeed;

    @JsonProperty("wind_degree")
    private Integer windDir;

    private int humidity;
    private int pressure;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public double getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDir() {
        return windDir;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
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
        return "Current{" +
                "temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", windDir=" + windDir +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

