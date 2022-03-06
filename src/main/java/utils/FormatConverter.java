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
}
