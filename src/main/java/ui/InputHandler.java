package ui;

import java.util.Scanner;

public class InputHandler {
    final Scanner input;

    Validator validator = new Validator();

    public InputHandler(Scanner input) {
        this.input = input;
    }

    public InputHandler(Scanner input, Validator validator) {
        this.input = input;
        this.validator = validator;
    }

    public String takeMenuChoice() {
        System.out.println("Chose an action 0-3");
        String checking;
        checking = input.nextLine();
        return checking;
    }

    public String takeLocationChoice() {
        System.out.println("Chose an action 0-2");
        String checking;
        checking = input.nextLine();
        return checking;
    }

    public String takeWeatherChoice() {
        System.out.println("Chose an action 0-3");
        String checking;
        checking = input.nextLine();
        return checking;
    }


    public String takeLocationCityName() {
        System.out.println("What's name of the City?");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.cityNameValidation(checking)) {
                System.out.println("Wrong Data try again[a-zA-Z]");
            }
        } while (!validator.cityNameValidation(checking));
        return checking;
    }

    public String takeLocationCountryName() {
        System.out.println("What's name of Country[a-zA-Z]?");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.countryNameValidation(checking)) {
                System.out.println("Wrong Data try again[a-zA-Z]");
            }
        } while (!validator.countryNameValidation(checking));
        return checking;
    }

    public String takeLocationRegionName() {
        System.out.println("What's name of Region[a-zA-Z]?");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.regionNameValidation(checking)) {
                System.out.println("Wrong Data try again[a-zA-Z]");
            }
        } while (!validator.regionNameValidation(checking));
        return checking;
    }


    public Double takeLocationLatitude() {
        System.out.println("What is Location Latitude?");
        Double checking;
        do {
            checking = input.nextDouble();
            if (!validator.validateLatitude(String.valueOf(checking))) {
                System.out.println("Wrong data try smth like 22,222");
            }
        } while (!validator.validateLatitude(String.valueOf(checking)));
        return checking;
    }


    public Double takeLocationLongitude() {
        System.out.println("What is Location Longitude?");
        Double checking;
        do {
            checking = input.nextDouble();
            if (!validator.validateLongitude(String.valueOf(checking))) {
                System.out.println("Wrong data try smth like 22,222");
            }
        } while (!validator.validateLongitude(String.valueOf(checking)));
        return checking;
    }


}
