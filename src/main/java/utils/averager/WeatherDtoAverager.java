package utils.averager;
import model.WeatherDto;
import utils.FormatConverter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class WeatherDtoAverager implements Averager<WeatherDto> {

    @Override
    public WeatherDto getAverage(WeatherDto[] dtos) {
        Double avgTemperature = getAverageTemperature(dtos);
        Integer avgHumidity = getAverageHumidity(dtos);
        Integer avgPressure = getAveragePressure(dtos);
        Integer avgWindSpeed = getAverageWindSpeed(dtos);
        Integer avgWindDegrees = getAverageWindDegrees(dtos);
        String windDirection = FormatConverter.getInstance().DegreesToCardinalDetailed(avgWindDegrees);

        return WeatherDto.Builder()
                .withTemperature(avgTemperature)
                .withHumidity(avgHumidity)
                .withPressure(avgPressure)
                .withWindSpeed(avgWindSpeed)
                .withWindDegrees(avgWindDegrees)
                .withWindDirection(windDirection)
                .build();
    }

    public Double round(Double toRound, int scale) {
        BigDecimal bd = new BigDecimal(Double.toString(toRound));
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Double getAverageLatitude(WeatherDto[] dtos) {
        double result = Arrays.stream(dtos)
                .filter(weather -> weather.getLatitude() != null)
                .mapToDouble(WeatherDto::getLatitude)
                .average()
                .orElse(0);


        return round(result, 4);
    }

    public Double getAverageLongitude(WeatherDto[] dtos) {
        double result = Arrays.stream(dtos)
                .filter(weather -> weather.getLongitude() != null)
                .mapToDouble(WeatherDto::getLongitude)
                .average()
                .orElse(0);

        return round(result, 4);
    }

    public Double getAverageTemperature(WeatherDto[] dtos) {
        double result = Arrays.stream(dtos)
                .filter(weather -> weather.getTemperature() != null)
                .mapToDouble(WeatherDto::getTemperature)
                .average()
                .orElse(0);

        return round(result, 1);
    }

    public Integer getAverageHumidity(WeatherDto[] dtos) {
        return (int) Arrays.stream(dtos)
                .filter(weather -> weather.getHumidity() != null)
                .mapToInt(WeatherDto::getHumidity)
                .average()
                .orElse(0);
    }

    public Integer getAveragePressure(WeatherDto[] dtos) {
        return (int) Arrays.stream(dtos)
                .filter(weather -> weather.getPressure() != null)
                .mapToInt(WeatherDto::getPressure)
                .average()
                .orElse(0);
    }

    public Integer getAverageWindSpeed(WeatherDto[] dtos) {
        return (int) Arrays.stream(dtos)
                .filter(weather -> weather.getWindSpeed() != null)
                .mapToInt(WeatherDto::getWindSpeed)
                .average()
                .orElse(0);
    }

    public Integer getAverageWindDegrees(WeatherDto[] dtos) {
        return (int) Arrays.stream(dtos)
                .filter(weather -> weather.getWindDegrees() != null)
                .mapToInt(WeatherDto::getWindDegrees)
                .average()
                .orElse(0);
    }

}
