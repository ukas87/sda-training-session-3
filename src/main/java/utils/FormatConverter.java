package utils;
public class FormatConverter {

    private static FormatConverter INSTANCE;

    private FormatConverter() {
    }

    public static FormatConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FormatConverter();
        }
        return INSTANCE;
    }

    public Integer fromKilometersHourToMeterSeconds(Integer toConvert){
        return (int) (toConvert/3.6);
    }

    public String DegreesToCardinalDetailed(Integer degrees){
        degrees *= 10;

        String[] cardinals = { "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N" };
        return cardinals[(int)Math.round(((double)degrees % 3600) / 225)];
    }
}
