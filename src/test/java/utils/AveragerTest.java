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
            .withLatitude(-80.54)
            .withLongitude(99.091)
            .build();

    final WeatherDto weatherDto2 = WeatherDto.Builder()
            .withCityName("Chicago1")
            .withCountryName("United States")
            .withRegion("Region1")
            .withTemperature(14.7)
            .withPressure(24)
            .withHumidity(78)
            .withWindSpeed(7)
            .withWindDegrees(350)
            .withLatitude(20.4444)
            .withLongitude(1.1)
            .build();

    final WeatherDto weatherDto3 = WeatherDto.Builder()
            .withCityName("Chicago2")
            .withCountryName("USA")
            .withRegion("Region one")
            .withTemperature(-7.0)
            .withPressure(5)
            .withHumidity(51)
            .withWindSpeed(1)
            .withWindDegrees(5)
            .withLatitude(19.1111)
            .withLongitude(15.29)
            .build();

    final WeatherDto weatherDto4 = WeatherDto.Builder().build();


    @Test
    void shouldReturnAverageWeatherDto(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};
        WeatherDto expected = WeatherDto.Builder()
                .withTemperature(19.1)
                .withPressure(13)
                .withHumidity(46)
                .withWindSpeed(4)
                .withWindDegrees(168)
                .withWindDirection("SSE")
                .withCityName("Chicago")
                .build();

        WeatherDto actual = averager.getAverage(dtos);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnFirstRegionName(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        String actual = averager.getRegionName(dtos);

        assertThat(actual).isEqualTo("Region1");
    }

    @Test
    void shouldReturnNullIfAllRegionsAreNull(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        String actual = averager.getRegionName(dtos);

        assertThat(actual).isNull();
    }

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

        String actual = averager.getCountryName(dtos);

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
    void shouldCountAverageLatitude(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Double actual = averager.getAverageLatitude(dtos);

        assertThat(actual).isEqualTo(-13.6615);
    }

    @Test
    void shouldCountAverageLatitudeWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Double actual = averager.getAverageLatitude(dtos);

        assertThat(actual).isEqualTo(-13.6615);
    }

    @Test
    void shouldReturnZeroAverageLatitudeIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Double actual = averager.getAverageLatitude(dtos);

        assertThat(actual).isEqualTo(0);
    }


    @Test
    void shouldCountAverageLongitude(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3};

        Double actual = averager.getAverageLongitude(dtos);

        assertThat(actual).isEqualTo(38.4937);
    }

    @Test
    void shouldCountAverageLongitudeWithOneEmptyField(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto1, weatherDto2, weatherDto3, weatherDto4};

        Double actual = averager.getAverageLongitude(dtos);

        assertThat(actual).isEqualTo(38.4937);
    }

    @Test
    void shouldReturnZeroAverageLongitudeIfAllFieldsEmpty(){
        WeatherDto[] dtos = new WeatherDto[]{weatherDto4};

        Double actual = averager.getAverageLongitude(dtos);

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
