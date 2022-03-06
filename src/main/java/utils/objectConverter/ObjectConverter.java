package utils.objectConverter;

public interface ObjectConverter<T,U> {
    U convert(T object);
}
