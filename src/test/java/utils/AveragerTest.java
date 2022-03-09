package utils;
import model.Weather;
import org.junit.jupiter.api.Test;
import utils.averager.WeatherAverager;
import static org.assertj.core.api.Assertions.assertThat;

public class AveragerTest {

    WeatherAverager averager = new WeatherAverager();

    Weather weather1 = Weather.Builder()
            .withTemperature(49.5)
            .withPressure(10)
            .withHumidity(11)
            .withWindSpeed(5)
            .withWindDegrees(150)
            .build();

    Weather weather2 = Weather.Builder()
            .withTemperature(14.7)
            .withPressure(24)
            .withHumidity(78)
            .withWindSpeed(7)
            .withWindDegrees(350)
            .build();

    Weather weather3 = Weather.Builder()
            .withTemperature(-7.0)
            .withPressure(5)
            .withHumidity(51)
            .withWindSpeed(1)
            .withWindDegrees(5)
            .build();

    Weather weather4 = Weather.Builder().build();


    @Test
    void shouldCountAverageTemperature(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3};

        Double actual = averager.getAverageTemperature(weathers);

        assertThat(actual).isEqualTo(19.1);
    }

    @Test
    void shouldCountAverageTemperatureWithOneEmptyField(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3,weather4};

        Double actual = averager.getAverageTemperature(weathers);

        assertThat(actual).isEqualTo(19.1);
    }

    @Test
    void shouldReturnZeroAverageTemperatureIfAllFieldsEmpty(){
        Weather[] weathers = new Weather[]{weather4};

        Double actual = averager.getAverageTemperature(weathers);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAveragePressureWithOneEmptyField(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3, weather4};

        Integer actual = averager.getAveragePressure(weathers);

        assertThat(actual).isEqualTo(13);
    }

    @Test
    void shouldReturnZeroAveragePressureIfAllFieldsEmpty(){
        Weather[] weathers = new Weather[]{weather4};

        Integer actual = averager.getAveragePressure(weathers);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void shouldCountAveragePressure(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3};

        Integer actual = averager.getAveragePressure(weathers);

        assertThat(actual).isEqualTo(13);
    }


    @Test
    void shouldCountAverageHumidity(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3};

        Integer actual = averager.getAverageHumidity(weathers);

        assertThat(actual).isEqualTo(46);
    }

    @Test
    void shouldCountAverageHumidityWithOneEmptyField(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3,weather4};

        Integer actual = averager.getAverageHumidity(weathers);

        assertThat(actual).isEqualTo(46);
    }

    @Test
    void shouldReturnZeroAverageHumidityIfAllFieldsEmpty(){
        Weather[] weathers = new Weather[]{weather4};

        Integer actual = averager.getAverageHumidity(weathers);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAverageWindSpeed(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3};

        Integer actual = averager.getAverageWindSpeed(weathers);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    void shouldCountAverageWindSpeedWithOneEmptyField(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3,weather4};

        Integer actual = averager.getAverageWindSpeed(weathers);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    void shouldReturnZeroAverageWindSpeedIfAllFieldsEmpty(){
        Weather[] weathers = new Weather[]{weather4};

        Integer actual = averager.getAverageWindSpeed(weathers);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAverageWindDegrees(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3};

        Integer actual = averager.getAverageWindDegrees(weathers);

        assertThat(actual).isEqualTo(168);
    }

    @Test
    void shouldCountAverageWindDegreesWithOneEmptyField(){
        Weather[] weathers = new Weather[]{weather1, weather2,weather3,weather4};

        Integer actual = averager.getAverageWindDegrees(weathers);

        assertThat(actual).isEqualTo(168);
    }

    @Test
    void shouldReturnZeroAverageWindDegreesIfAllFieldsEmpty(){
        Weather[] weathers = new Weather[]{weather4};

        Integer actual = averager.getAverageWindDegrees(weathers);

        assertThat(actual).isEqualTo(0);
    }
}
