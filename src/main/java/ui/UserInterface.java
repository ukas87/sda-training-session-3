package ui;
import dao.LocationDao;
import dao.WeatherDao;
import model.Location;
import model.WeatherDto;
import service.LocationService;
import service.OpenWeatherMapClient;
import service.WeatherService;
import service.WeatherStackClient;
import utils.averager.WeatherDtoAverager;
import utils.mapper.LocationMapper;
import utils.mapper.WeatherMapper;

import java.util.Scanner;

public class UserInterface {

    final InputHandler inputHandler = new InputHandler(new Scanner(System.in));
    LocationService locationService = new LocationService(new LocationDao(), new LocationMapper());
    WeatherService weatherService = new WeatherService(new OpenWeatherMapClient(), new WeatherStackClient(), new WeatherDtoAverager(), new WeatherMapper(), new LocationMapper(), new LocationDao(), new WeatherDao());
    boolean isRunning = true;
    final String initMenu = "==== Weather Application ===\n[1] Adding a location\n[2] Display currently added locations\n[3] Downloading weather values\n[0] Exit";

    public void userMenu() {
        do {
            System.out.println(initMenu);
            String choice = inputHandler.takeMenuChoice();

            switch (choice) {
                case "1" -> {
                    System.out.println("You chose to Add a location: ");
                    Location locationToAdd = Location.Builder()
                            .withCityName(inputHandler.takeLocationCityName())
                            .withCountryName(inputHandler.takeLocationCountryName())
                            .withRegion(inputHandler.takeLocationRegionName())
                            .withLatitude(inputHandler.takeLocationLatitude())
                            .withLongitude(inputHandler.takeLocationLongitude())
                            .build();
                    locationService.add(locationToAdd);
                }
                case "2" -> {
                    System.out.println("You chose to display Available Locations: ");
                    locationService.displayLocations(locationService.getAllLocations());
                }

                case "3" -> {
                    System.out.println("You chose to Download Weather Values: ");
                    weatherDownloadMenu();
                }
                //showWeather
                case "0" -> {
                    System.out.println("Bye, thanks");
                    isRunning = false;
                }
                default -> System.out.println("(default)Wrong data! Try again\n");
            }
        } while (isRunning);
    }

    public void weatherDownloadMenu() {
        try {
            WeatherDto weatherDto = weatherService.getAverageWeatherDtoByCityNameFromBase(inputHandler.takeLocationCityName());
            weatherService.saveWeather(weatherDto);
            weatherService.displayWeather(weatherDto);
        } catch (Exception e) {
            System.err.println("Unable to get data for your city");
        }
    }
}

