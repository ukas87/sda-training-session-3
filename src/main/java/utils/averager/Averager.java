package utils.averager;

import dto.WeatherDto;

public interface Averager<T> {

    T getAverage(WeatherDto[] objects);


}
