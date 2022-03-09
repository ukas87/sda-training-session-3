package ui;

import model.Location;

import service.LocationService;
import java.util.Scanner;

public class UserInterface {


    final InputHandler inputHandler = new InputHandler(new Scanner(System.in));

    String PATH = "locationData.csv";
    LocationService locationService = new LocationService();
    boolean isRunning = true;
    final String initMenu = "==== Weather Application ===\n[1] Adding a location\n[2] Display currently added locations\n[3] Downloading weather values\n[0] Exit";

    public void userMenu() {
        do {
            System.out.println(initMenu);
            String choice = inputHandler.takeMenuChoice();


            switch (choice) {
                case "1":
                    System.out.println("You chose to Add a location: ");
                    Location locationToAdd = Location.Builder()
                            .withCityName(inputHandler.takeLocationCityName())
                            .withCountryName(inputHandler.takeLocationCountryName())
                            .withRegion(inputHandler.takeLocationRegionName())
                            .withLatitude(inputHandler.takeLocationLatitude())
                            .withLongitude(inputHandler.takeLocationLongitude())
                            .build();

                    //locationService.write(locationToAdd);
                    break;

                case "2":
                    System.out.println("You chose to display Current Location: ");
                    //locationService.displayAllLocations();
                    break;

                case "3":
                    System.out.println("You chose to Download Weather Values: ");
                    //showWeather
                    break;

                case "0":
                    System.out.println("Bye, thanks");
                    isRunning = false;
                    break;
                default:
                    System.out.println("(default)Wrong data! Try again\n");
            }
        } while (isRunning);
    }
}

