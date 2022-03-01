package model.openweathermap;

public class Main {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;


    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", feels_like=" + feels_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
