package model;

import java.time.LocalDateTime;

public class Weather {

    private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Integer windSpeed;
    private LocalDateTime date;

    public static class Builder {
        private final Weather newWeather;

        public Builder() {
            newWeather = new Weather();
        }

        public Builder withTemperature(Double temperature) {
            newWeather.temperature = temperature;
            return this;
        }

        public Builder withPressure(Integer pressure) {
            newWeather.pressure = pressure;
            return this;
        }

        public Builder withHumidity(Integer humidity) {
            newWeather.humidity = humidity;
            return this;
        }

        public Builder withWindSpeed(Integer windSpeed) {
            newWeather.windSpeed = windSpeed;
            return this;
        }

        public Builder withDate(LocalDateTime localDateTime) {
            newWeather.date = localDateTime;
            return this;
        }

        public Weather build() {
            return newWeather;
        }
    }

    @Override
    public String toString() {
        return "" + temperature + "," + pressure + "," + humidity + "," + windSpeed + "," + date;
    }
}
