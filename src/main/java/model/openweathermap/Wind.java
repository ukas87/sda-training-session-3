package model.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
    @JsonProperty("speed")
    private int speed;

    @JsonProperty("deg")
    private int deg;

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
