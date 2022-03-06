package utils.objectConverter;

import model.Weather;

public class WeatherAverager implements Averager<Weather> {

    @Override
    public Weather getAverage(Weather[] weathers) {
        Double avgTemperature = getAverageTemperature(weathers);
        Integer avgHumidity = getAverageHumidity(weathers);


        return null;
    }

    private Double getAverageTemperature(Weather[] weathers) {
        Double result = 0D;
        for (Weather weather : weathers) {
            result += weather.getTemperature();
        }
        return result / weathers.length;
    }

    private Integer getAverageHumidity(Weather[] weathers) {
        Integer result = 0;
        for (Weather weather : weathers) {
            result += weather.getHumidity();
        }
        return result / weathers.length;
    }

}
