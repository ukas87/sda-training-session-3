package utils.averager;


import java.util.List;

public interface Averager<T> {

    T getAverage(List<T> objects);


}
