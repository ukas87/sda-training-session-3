package model.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"coord", "weather", "base", "visibility", "clouds", "dt", "sys", "timezone", "id", "name", "cod"})
public class OpenWeatherMapForecast {

    private Main main;
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
