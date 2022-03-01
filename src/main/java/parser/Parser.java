package parser;

import java.util.List;

public interface Parser<T> {

    List<T> getObjectFromFile();
    String getPath();

}
