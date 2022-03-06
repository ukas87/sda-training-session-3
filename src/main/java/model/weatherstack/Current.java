package model.weatherstack;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Current {

    private double temperature;
    private int wind_speed;
    private String wind_dir;
    private int humidity;
    private int pressure;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public double getTemperature() {
        return temperature;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public String getWind_dir() {
        return wind_dir;
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
                "temp=" + temperature +
                ", windSpeed=" + wind_speed+
                ", windDir='" + wind_dir + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}

