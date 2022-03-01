package model.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties({"coord", "weather", "base", "visibility", "clouds", "dt", "sys", "timezone", "id", "name", "cod"})
public class OpenWeatherMapForecast {

    @JsonProperty("main")
    private Main main;
    @JsonProperty("wind")
    private Wind wind;

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return "OpenWeatherMapForecast{" + "main=" + main + ", wind=" + wind + '}';
    }
}
