package model.openweathermap;

public class Wind {
    public int speed;
    public int deg;


    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
