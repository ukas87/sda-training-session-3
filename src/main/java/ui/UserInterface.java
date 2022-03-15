package ui;

import dao.LocationDao;
import dao.WeatherDao;
import model.Location;

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
    WeatherService weatherService = new WeatherService(new WeatherStackClient(), new OpenWeatherMapClient(), new WeatherDtoAverager(), new WeatherMapper(), new LocationMapper(), new LocationDao(), new WeatherDao());
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
                //locationService.displayAllLocations();
                case "3" -> System.out.println("You chose to Download Weather Values: ");

                //showWeather
                case "0" -> {
                    System.out.println("Bye, thanks");
                    isRunning = false;
                }
                default -> System.out.println("(default)Wrong data! Try again\n");
            }
        } while (isRunning);
    }
}

