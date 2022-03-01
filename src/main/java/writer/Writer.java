package writer;

public interface Writer<T> {
    void write(T object);
    void eraseAllData();
}