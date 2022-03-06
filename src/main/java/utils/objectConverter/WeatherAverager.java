package utils.objectConverter;

import model.Weather;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class WeatherAverager implements Averager<Weather> {

    @Override
    public Weather getAverage(Weather[] weathers) {
        Double avgTemperature = getAverageTemperature(weathers);
        Integer avgHumidity = getAverageHumidity(weathers);
        Integer avgPressure = getAveragePressure(weathers);
        Integer avgWindSpeed = getAverageWindSpeed(weathers);


        return new Weather.Builder()
                .withTemperature(avgTemperature)
                .withHumidity(avgHumidity)
                .withPressure(avgPressure)
                .withWindSpeed(avgWindSpeed)
                .build();
    }

    public Double getAverageTemperature(Weather[] weathers) {
        double result = Arrays.stream(weathers)
                .filter(weather -> weather.getTemperature() != null)
                .mapToDouble(Weather::getTemperature)
                .average()
                .orElse(0);

        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Integer getAverageHumidity(Weather[] weathers) {
        return (int) Arrays.stream(weathers)
                .filter(weather -> weather.getHumidity() != null)
                .mapToInt(Weather::getHumidity)
                .average()
                .orElse(0);
    }

    public Integer getAveragePressure(Weather[] weathers) {
        return (int) Arrays.stream(weathers)
                .filter(weather -> weather.getPressure() != null)
                .mapToInt(Weather::getPressure)
                .average()
                .orElse(0);
    }

    public Integer getAverageWindSpeed(Weather[] weathers) {
        return (int) Arrays.stream(weathers)
                .filter(weather -> weather.getWindSpeed() != null)
                .mapToInt(Weather::getWindSpeed)
                .average()
                .orElse(0);
    }

}
