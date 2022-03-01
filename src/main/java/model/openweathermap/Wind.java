package model.openweathermap;
public class Wind {

    private int speed;
    private int deg;

    public int getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
