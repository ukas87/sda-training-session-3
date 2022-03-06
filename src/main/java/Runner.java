import model.Weather;
import model.openweathermap.OpenWeatherMapForecast;
import model.weatherstack.WeatherStackMapForecast;
import service.OpenWeatherMapClient;
import service.WeatherClient;
import service.WeatherStackMapClient;
import ui.UserInterface;
import utils.ObjectConverter;
import utils.OpenWeatherMapToWeatherConverter;
import utils.WeatherStackToWeatherConverter;

public class Runner {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.userMenu();
        WeatherClient<OpenWeatherMapForecast> wc = new OpenWeatherMapClient();
        OpenWeatherMapForecast forecast = wc.getWeatherByCity("Gdańsk");
        System.out.println(forecast);
        ObjectConverter<OpenWeatherMapForecast, Weather> converter = OpenWeatherMapToWeatherConverter.getInstance();
        Weather weather = converter.convert(forecast);
        System.out.println(weather);

        WeatherClient<WeatherStackMapForecast> ws = new WeatherStackMapClient();
        WeatherStackMapForecast forecast2 = ws.getWeatherByCity("Gdansk");
        System.out.println(forecast2);
        ObjectConverter<WeatherStackMapForecast,Weather> converter2 = WeatherStackToWeatherConverter.getInstance();
        Weather weather2 = converter2.convert(forecast2);
        System.out.println(weather2);

    }
}
