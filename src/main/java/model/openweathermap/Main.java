package model.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"feels_like", "temp_min", "temp_max"})
public class Main {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
