package utils.averager;
import dto.WeatherDto;
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

    public Double getAverageTemperature(WeatherDto[] dtos) {
        double result = Arrays.stream(dtos)
                .filter(weather -> weather.getTemperature() != null)
                .mapToDouble(WeatherDto::getTemperature)
                .average()
                .orElse(0);

        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
