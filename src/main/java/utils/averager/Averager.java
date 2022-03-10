package utils.averager;

import model.WeatherDto;

public interface Averager<T> {

    T getAverage(WeatherDto[] objects);


}
