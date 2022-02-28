package service;

import model.WeatherObject;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherService {

    List<WeatherObject> weatherObjects;
    private static final String PATH = "weatherData.csv";


    public WeatherService(List<WeatherObject> weatherObjects) {
        this.weatherObjects = weatherObjects;
    }

    public void add(WeatherObject weatherObject) {
        weatherObjects.add(weatherObject);
    }

    public void remove(WeatherObject weatherObject) {
        weatherObjects.remove(weatherObject);
    }

    public List<WeatherObject> getWeatherObjectsFromFile() {
        List<String[]> lines = getLinesFromFile();

        return lines.stream()
                .map(line -> new WeatherObject(UUID.fromString(line[0]),
                        Long.parseLong(line[1]),
                        Long.parseLong(line[2]),
                        line[3],
                        line[4],
                        line[5]))
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

    public void write(WeatherObject weatherObject) {
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    (weatherObject.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
