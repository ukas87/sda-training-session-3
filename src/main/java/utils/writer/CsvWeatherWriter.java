package utils.writer;
import model.Weather;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CsvWeatherWriter implements Writer<Weather> {

    private final String PATH;

    public CsvWeatherWriter(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public void write(Weather weather) {
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    (weather.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to write to the file.");
        }
    }

    @Override
    public void eraseAllData() {
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    ("".getBytes()));
        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to erase all data");
        }
    }
}

