package utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;


public class FormatConverter {

    private static FormatConverter INSTANCE;

    protected FormatConverter() {
    }

    public static FormatConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FormatConverter();
        }
        return INSTANCE;
    }

    public Integer fromKilometersHourToMeterSeconds(Integer toConvert) {
        return (int) (toConvert / 3.6);
    }

    public String DegreesToCardinalDetailed(Integer degrees) {
        degrees *= 10;

        String[] caridnals = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N"};
        return caridnals[(int) Math.round(((double) degrees % 3600) / 225)];
    }

    public Double longitudeFormatter(Double longitude) {
        return (Math.round(longitude * 1000.0) / 1000.0);
    }

    public Double latitudeFormatter(Double latitude) {
        return (Math.round(latitude * 1000.0) / 1000.0);
    }

    public static final Pattern DIACRITICS_AND_FRIENDS
            = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    public String cityNameFormatters(String city) {
        city = Normalizer.normalize(city, Normalizer.Form.NFD);
        city = DIACRITICS_AND_FRIENDS.matcher(city).replaceAll("");
        return city;
    }

    public String regionNameFormatter() {
        return regionNameFormatter();
    }

    public Double temperatureFormatter(double n) {
        return (Math.round(n * 100.0) / 100.0);
    }

    public Integer pressureFormatter() {
        return null;
    }

    public Integer humidityFormatter() {
        return null;

    }
    public String countryNameFormatter(String country) {
        Locale l = new Locale(" ", country);
        return l.getDisplayCountry(Locale.ENGLISH);
    }
}
