import service.OpenWeatherMapClient;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherMapClient ow = new OpenWeatherMapClient();
        System.out.println(ow.getResponse("Gdansk"));

    }
}
