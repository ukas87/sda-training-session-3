package ui;
import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import model.Weather;
import model.WeatherDto;
import service.LocationService;
import service.OpenWeatherMapClient;
import service.WeatherService;
import service.WeatherStackClient;
import utils.averager.WeatherDtoAverager;
import utils.mapper.LocationMapper;
import utils.mapper.WeatherMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    final InputHandler inputHandler = new InputHandler(new Scanner(System.in), new Validator());
    LocationService locationService = new LocationService(new LocationDao(), new LocationMapper());
    WeatherService weatherService = new WeatherService(new OpenWeatherMapClient(), new WeatherStackClient(), new WeatherDtoAverager(), new WeatherMapper(), new LocationMapper(), new LocationDao(), new WeatherDao());
    boolean isRunning = true;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET= "\033[0m";




    public void startMenu() {
        menuInterface();
        while (isRunning) {
            String  choice = inputHandler.takeMenuChoice();
            switch (choice) {
                case "1" ->
                    locationMenu();

                case "2" ->
                    weatherMenu();
                
                case "0" -> {
                    System.out.println("Bye, thanks");
                    isRunning = false;
                }
            }
        }
    }

    public void locationMenu() {

            locationInterface();
           String choice = inputHandler.takeLocationChoice();

            switch (choice) {
                case "1" -> {
                    System.out.println("You chose to Add a location: ");
                    addLocation();
                    startMenu();
                    System.out.println("Test1");
                }
                case "2" -> {
                    System.out.println("You chose to display available Locations: ");
                    System.out.println();
                    displayAvailableLocations();
                    startMenu();
                }
                case "3" -> {
                    System.out.println("You chose to delete Location from available Locations: ");
                    System.out.println();
                    deleteLocation();
                    startMenu();
                }
                case "4" -> {
                    System.out.println("You chose to update Location parameters: ");
                    updateLocation();
                    startMenu();
                }
                case "0" -> {
                    startMenu();
                    System.out.println();
                }
            }
        }

    public void weatherMenu() {
            weatherInterface();
          String choice = inputHandler.takeWeatherChoice();

            switch (choice) {
                case "1" -> {
                    System.out.println("You chose to display average weather by city");
                    displayAverageWeather();
                    startMenu();
                }
                case "2" -> {
                    System.out.println("You chose to display historic average weather by city and date");
                    displayHistoricAverageWeatherByCyAndDate();
                    startMenu();
                }
                case "0" -> {
                    startMenu();
                    System.out.println();
                }
            }
        }

    public void addLocation() {
        try {
            Location locationToAdd = Location.Builder()
                    .withCityName(inputHandler.takeLocationCityName())
                    .withCountryName(inputHandler.takeLocationCountryName())
                    .withRegion(inputHandler.takeLocationRegionName())
                    .withLatitude(inputHandler.takeLocationLatitude())
                    .withLongitude(inputHandler.takeLocationLongitude())
                    .build();

            locationService.add(locationToAdd);
            System.out.println(ANSI_GREEN + "New location successful added" + ANSI_RESET);
        } catch (Exception e) {
            System.err.println("Location was not added");
        }
    }

    public void deleteLocation(){
        try{
            locationService.deleteLocationWithWeatherRelatedByCityName(inputHandler.takeLocationCityName());
            System.out.println(ANSI_GREEN +"Location successfully deleted" + ANSI_RESET);
            System.out.println();
        } catch (Exception e){
            System.err.println("Unable to delete location, int was not found in DB");
        }
    }

    public void updateLocation(){
        try{
            Location locationToUpdate = locationService.findByCity(inputHandler.takeLocationCityName());
                    locationToUpdate.setCityName(inputHandler.takeLocationCityName());
                    locationToUpdate.setCountryName(inputHandler.takeLocationCountryName());
                    locationToUpdate.setRegion(inputHandler.takeLocationRegionName());
                    locationToUpdate.setLongitude(inputHandler.takeLocationLongitude());
                    locationToUpdate.setLatitude(inputHandler.takeLocationLatitude());

            locationService.update(locationToUpdate);
            System.out.println(ANSI_GREEN + "You updated locations parameters in DB" + ANSI_RESET);
        } catch (Exception e){
            System.err.println("Unable to update location, it is not in DB");
        }
    }


    public void displayAvailableLocations() {
        try {
            locationService.displayAllLocations();
            System.out.println(ANSI_GREEN + "All available locations have been displayed" + ANSI_RESET);
        } catch (Exception e) {
            System.err.println("Unable to get Location data, it is not in DB");
        }
    }

    public void displayAverageWeather() {
        try {
            WeatherDto weatherDto = weatherService.getAverageWeatherDtoByCityNameFromBase(inputHandler.takeLocationCityName());
            weatherService.saveWeather(weatherDto);
            weatherService.displayWeather(weatherDto);
            System.out.println(ANSI_GREEN + "Average weather for was successful displayed" + ANSI_RESET);
        } catch (Exception e) {
            System.err.println("Unable to get data for your city. The city name is not in DB");
        }
    }


    public void displayHistoricAverageWeatherByCyAndDate() {
        try {
            List<Weather> weather =  weatherService.getAllWeathersByDate(LocalDate.parse(inputHandler.takeLocalDate()), inputHandler.takeLocationCityName2());
            weatherService.displayWeathers(weather);
            System.out.println(ANSI_GREEN + "Historic data by city name was displayed successful" + ANSI_RESET);
        } catch (Exception e) {
            System.err.println("Unable to display historic data by city name, are you sure city name is in DB?");
        }
    }

    public void menuInterface(){
        System.out.println("""
                
                ==== Weather Application ===
                [1] Location Menu
                [2] Weather Menu
                [0] Exit
                """);
    }

    public void locationInterface(){
        System.out.println("""
                
                =========== Location Menu ==========
                [1] Adding a location to DB
                [2] Display all locations in DB
                [3] Delete location from DB
                [4] Update location parameters in DB
                [0] Back to Init Menu
                """);
    }

    public void weatherInterface(){
        System.out.println("""
                
                ============== Weather Menu ================
                [1] Display average weather by city name
                [2] Display historic average weather by city
                [0] Back to Init Menu
                """);
    }

}