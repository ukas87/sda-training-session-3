package utils.mapper;

public interface Mapper<T,U> {

    T toEntity(U entity);
    U toDto(T dto);
}
