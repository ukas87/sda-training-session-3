package utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FormatConverterTest {


    FormatConverter formatConverter = new FormatConverter();

    @Test
    public void checkLatitudeFormatConverter(){

        Double result = formatConverter.latitudeFormatter(33.333);

        assertThat(result).isNotNull();
    }

    @Test
    public void iscCheckLatitudeFormatConverterWorking(){

        Double result = formatConverter.latitudeFormatter(33.3332);

        assertThat(result).isEqualTo(33.333);
    }

    @Test
    public void checkLongitudeFormatConverter(){
        Double result = formatConverter.longitudeFormatter(175.22);

        assertThat(result).isNotNull();
    }

    @Test
    public void isCheckLongitudeFormatConverterWorking(){
        Double result = formatConverter.longitudeFormatter(175.2222);

        assertThat(result).isEqualTo(175.222);
    }

    @Test
    public void checkTemperatureFormatConverter(){
        Double result = formatConverter.temperatureFormatter(12.222);

        assertThat(result).isEqualTo(12.22);
    }

    @Test
    public void checkCityNameFormatter(){

        String result = formatConverter.cityNameFormatters("ĄĘŹŻ");

        assertThat(result).isEqualTo("AEZZ");
    }

    @Test
    public void checkCountryNameFormatterCorrect(){

        String result = formatConverter.countryNameFormatter("PL");

        assertThat(result).isEqualTo("Poland");
        assertThat(result).isNotNull();

    }
}