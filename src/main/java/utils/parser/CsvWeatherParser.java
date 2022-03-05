package utils.parser;
import model.Weather;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWeatherParser implements Parser<Weather>{

    private final String PATH;

    public CsvWeatherParser(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public List<Weather> getObjectFromFile() {
        List<String[]> lines = getLinesFromFile();
        return lines.stream()
                .map(line -> new Weather.Builder()
                        .withTemperature(Double.parseDouble(line[0]))
                        .withPressure(Integer.parseInt(line[1]))
                        .withHumidity(Integer.parseInt(line[2]))
                        .withWindSpeed(Integer.parseInt(line[3]))
                        .withDate(LocalDateTime.parse(line[4]))
                        .build())
                .collect(Collectors.toList());
    }

    private List<String[]> getLinesFromFile() {
        List<String[]> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(PATH).toURI()))) {
            lines = stream
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to read the file.");
        }
        return lines;
    }
}
