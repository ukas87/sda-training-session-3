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

    final InputHandler inputHandler = new InputHandler(new Scanner(System.in));
    LocationService locationService = new LocationService(new LocationDao(), new LocationMapper());
    WeatherService weatherService = new WeatherService(new OpenWeatherMapClient(), new WeatherStackClient(), new WeatherDtoAverager(), new WeatherMapper(), new LocationMapper(), new LocationDao(), new WeatherDao());
    boolean isRunning = true;


    public void startMenu() {
        do {
            menuInterface();
            String choice = inputHandler.takeMenuChoice();
            switch (choice) {
                case "1" -> {
                    locationMenu();
                }
                case "2" -> {
                    weatherMenu();
                }
                case "0" -> {
                    System.out.println("Bye, thanks");
                    isRunning = false;
                }
                default -> System.out.println("(default)Wrong data! Try again\n");
            }
        } while (isRunning);
    }


    public void locationMenu() {
        locationInterface();
        String choice = inputHandler.takeLocationChoice();
        switch (choice) {
            case "1" -> {
                System.out.println("You chose to Add a location: ");
                addLocation();
            }
            case "2" -> {
                System.out.println("You chose to display available Locations: ");
                displayAvailableLocations();
            }
            case "3" -> {
                System.out.println("You chose to delete Location from available Locations: ");
                deleteLocation();
            }
            case "4" -> {
                System.out.println("You chose to update Location parameters: ");
                updateLocation();
            }
            case "0" -> {
                menuInterface();
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
                System.out.println();
            }
            case "2" -> {
                System.out.println("You chose to display historic average weather by city and date");
                displayHistoricAverageWeatherByCyAndDate();
            }
            case "0" -> {
                menuInterface();
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
        } catch (Exception e) {
            System.err.println("Wrong data");
        }
    }

    public void deleteLocation(){
        try{
            locationService.delete(locationService.findByCity(inputHandler.takeLocationCityName()));
            System.out.println("Location successfully deleted");
            System.out.println();
        } catch (Exception e){
            System.err.println("Unable to delete location");
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
            System.out.println("You updated locations parameters");
        } catch (Exception e){
            System.err.println("Unable to update location");
        }
    }


    public void displayAvailableLocations() {
        try {
            locationService.displayLocations(locationService.getAllLocations());
        } catch (Exception e) {
            System.err.println("Unable to get data");
        }
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

    public void displayAverageWeather() {
        try {
            WeatherDto weatherDto = weatherService.getAverageWeatherDtoByCityNameFromBase(inputHandler.takeLocationCityName());
            weatherService.saveWeather(weatherDto);
            weatherService.displayWeather(weatherDto);
        } catch (Exception e) {
            System.err.println("Unable to get data for your city");
        }
    }


    public void displayHistoricAverageWeatherByCyAndDate() {
        try {
            WeatherDto weatherDto = (WeatherDto) weatherService.getAllWeathersByDate(LocalDate.parse(inputHandler.takeLocalDate()), inputHandler.takeLocationCityName2());
            weatherService.displayWeathers((List<Weather>) weatherDto);
            //WeatherDto weathersDto = (WeatherDto) weatherService.getAllWeathersByDate(inputHandler.takeLocalDate(), inputHandler.takeLocationCityName());
            //weatherService.displayWeathers((List<Weather>) weathersDto);
        } catch (Exception e) {
            System.err.println("Wrong historic data");
        }
    }



    public void menuInterface(){
        System.out.println("==== Weather Application ===\n[1] Location Menu\n[2] Weather Menu\n[0] Exit");
    }

    public void locationInterface(){
        System.out.println("==== Location Menu ===\n[1] Adding a location to DB\n[2] Display all locations in DB\n" +
                "[3] Delete location from DB\n[4] Update location parameters in DB\n[0] Back to Init Menu");
    }

    public void weatherInterface(){
        System.out.println("==== Weather Menu ===\n[1] Display average weather by city name\n[2] Display historic" +
                " average weather by city\n[0] Back to Init Menu");
    }

}