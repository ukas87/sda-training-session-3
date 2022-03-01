package utils;

public interface ObjectConverter<T,U> {
    U convert(T object);
}
