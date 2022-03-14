package utils;
import model.WeatherDto;
import org.junit.jupiter.api.Test;
import utils.averager.WeatherDtoAverager;
import static org.assertj.core.api.Assertions.assertThat;

public class AveragerTest {

    final WeatherDtoAverager averager = new WeatherDtoAverager();

    final WeatherDto weatherDto1 = WeatherDto.Builder()
            .withCountryName("United States Of America")
            .withCityName("Chicago")
            .withTemperature(49.5)
            .withPressure(10)
            .withHumidity(11)
            .withWindSpeed(5)
            .withWindDegrees(150)
            .build();

    final WeatherDto weatherDto2 = WeatherDto.Builder()
            .withCityName("Chicago1")
            .withCountryName("United States")
            .withTemperature(14.7)
            .withPressure(24)
            .withHumidity(78)
            .withWindSpeed(7)
            .withWindDegrees(350)
            .build();

    final WeatherDto weatherDto3 = WeatherDto.Builder()
            .withCityName("Chicago2")
            .withCountryName("USA")
            .withTemperature(-7.0)
            .withPressure(5)
            .withHumidity(51)
            .withWindSpeed(1)
            .withWindDegrees(5)
            .build();

    final WeatherDto weatherDto4 = WeatherDto.Builder().build();

    @Test
    void shouldReturnFirstCityName(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        String actual = averager.getCityName(dtos);

        assertThat(actual).isEqualTo("Chicago");
    }

    @Test
    void shouldReturnNullIfAllCitiesAreNull(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        String actual = averager.getCityName(dtos);

        assertThat(actual).isNull();
    }

    @Test
    void shouldReturnCountryNameInProperFormat(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        String actual = averager.getCountryName(dtos);

        assertThat(actual).isEqualTo("USA");
    }

    @Test
    void shouldReturnNullIfAllCountriesAreNull(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        String actual = averager.getCityName(dtos);

        assertThat(actual).isNull();
    }


    @Test
    void shouldCountAverageTemperature(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Double actual = averager.getAverageTemperature(dtos);

        assertThat(actual).isEqualTo(19.1);
    }

    @Test
    void shouldCountAverageTemperatureWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Double actual = averager.getAverageTemperature(dtos);

        assertThat(actual).isEqualTo(19.1);
    }

    @Test
    void shouldReturnZeroAverageTemperatureIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Double actual = averager.getAverageTemperature(dtos);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAveragePressureWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Integer actual = averager.getAveragePressure(dtos);

        assertThat(actual).isEqualTo(13);
    }

    @Test
    void shouldReturnZeroAveragePressureIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Integer actual = averager.getAveragePressure(dtos);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void shouldCountAveragePressure(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Integer actual = averager.getAveragePressure(dtos);

        assertThat(actual).isEqualTo(13);
    }


    @Test
    void shouldCountAverageHumidity(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Integer actual = averager.getAverageHumidity(dtos);

        assertThat(actual).isEqualTo(46);
    }

    @Test
    void shouldCountAverageHumidityWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Integer actual = averager.getAverageHumidity(dtos);

        assertThat(actual).isEqualTo(46);
    }

    @Test
    void shouldReturnZeroAverageHumidityIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Integer actual = averager.getAverageHumidity(dtos);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAverageWindSpeed(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Integer actual = averager.getAverageWindSpeed(dtos);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    void shouldCountAverageWindSpeedWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Integer actual = averager.getAverageWindSpeed(dtos);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    void shouldReturnZeroAverageWindSpeedIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Integer actual = averager.getAverageWindSpeed(dtos);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAverageWindDegrees(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Integer actual = averager.getAverageWindDegrees(dtos);

        assertThat(actual).isEqualTo(168);
    }

    @Test
    void shouldCountAverageWindDegreesWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Integer actual = averager.getAverageWindDegrees(dtos);

        assertThat(actual).isEqualTo(168);
    }

    @Test
    void shouldReturnZeroAverageWindDegreesIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Integer actual = averager.getAverageWindDegrees(dtos);

        assertThat(actual).isEqualTo(0);
    }
}
