import dao.LocationDao;
import dao.WeatherDao;
import model.Weather;
import service.WeatherService;
import service.WeatherStackClient;
import utils.averager.WeatherDtoAverager;
import utils.mapper.Mapper;
import model.Location;
import model.openweathermap.OpenWeatherMapForecast;
import service.OpenWeatherMapClient;
import service.WeatherClient;

public class Runner {
    public static void main(String[] args) {
        WeatherClient<OpenWeatherMapForecast> wc = new OpenWeatherMapClient();
        Mapper mapper = new Mapper();
        OpenWeatherMapForecast forecast = wc.getWeatherByCity("Chicago");
        System.out.println(mapper.dtoToWeather(mapper.OpenWeatherToWeatherDto(forecast)));
        LocationDao lDao = new LocationDao();
        WeatherDao wDao = new WeatherDao();

        WeatherService service = new WeatherService(wc, new WeatherStackClient(), new WeatherDtoAverager(), new Mapper(), lDao, wDao);
        Location location = Location.Builder()
                .withCityName("New York")
                .withCountryName("US")
                .withLatitude(123.132)
                .withLongitude(321.321)
                .withRegion("CloseToDemocracy")
                .build();
        Weather weather = Weather.Builder()
                .withTemperature(-12.00)
                .withHumidity(5)
                .withPressure(10)
                .withWindDegrees(123)
                .withWindDirection("NW")
                .withLocation(location)
                .build();

        service.saveWeather(weather);
    }
}
