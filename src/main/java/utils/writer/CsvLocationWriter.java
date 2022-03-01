package utils.writer;
import model.Location;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CsvLocationWriter implements Writer<Location> {

    private final String PATH;

    public CsvLocationWriter(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public void write(Location location) {
        try {
            Files.write((Paths.get(ClassLoader.getSystemResource(PATH).toURI())),
                    (location.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
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
