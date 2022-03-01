package model.openweathermap;

public class Sys {
   public int type;
   public int id;
   public String country;
   public int sunrise;
   public int sunset;


    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
