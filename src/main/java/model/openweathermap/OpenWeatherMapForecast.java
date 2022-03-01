package model.openweathermap;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"coord", "weather", "base", "visibility", "clouds", "dt", "sys", "timezone", "id", "name", "cod", "rain"})
public class OpenWeatherMapForecast {

    private Main main;
    private Wind wind;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
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
        return "OpenWeatherMapForecast{" + "main=" + main + ", wind=" + wind + '}';
    }
}
