package ui;
import java.util.Scanner;

public class InputHandler {

    private final Scanner input;
    private final Validator validator;

    public InputHandler(Scanner input, Validator validator) {
        this.input = input;
        this.validator = validator;
    }

    public String takeMenuChoice() {
        System.out.println("Chose an action 0-2");
        String checking;
        checking = input.nextLine();
        return checking;
    }

    public String takeLocationChoice() {
        System.out.println("Chose an action 0-4");
        String checking;
        checking = input.nextLine();
        return checking;
    }

    public String takeWeatherChoice() {
        System.out.println("Chose an action 0-2");
        String checking;
        checking = input.nextLine();
        return checking;
    }

    public String takeLocationCityName() {
        System.out.println("What's name of the City? [start with capital letter]");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.cityNameValidation(checking)) {
               System.out.println("Wrong data format, try again [start with capital letter]");
            }
        } while (!validator.cityNameValidation(checking));
        return checking;
    }

    public String takeLocationCityName2() {
        System.out.println("What's name of the City?");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.cityNameValidation(checking)) {
            }
        } while (!validator.cityNameValidation(checking));
        return checking;
    }

    public String takeLocationCountryName() {
        System.out.println("What's name of Country? [start with capital letter]");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.countryNameValidation(checking)) {
                System.out.println("Wrong data format, try again [start with capital letter]");
            }
        } while (!validator.countryNameValidation(checking));
        return checking;
    }

    public String takeLocationRegionName2() {
        System.out.println("What's name of Region?");
        String checking;
        do {
            checking = input.nextLine();
            if (!validator.regionNameValidation(checking)) {
                System.out.println("Wrong Data try again[a-zA-Z]");
            }
        } while (!validator.regionNameValidation(checking));
        return checking;
    }

    public String takeLocationRegionName() {
        System.out.println("What's name of Region?");
        String checking;

            checking = input.nextLine();
            if (checking.equals("")) {
                checking = null;
            }
            return checking;
        }


    public Double takeLocationLatitude() {
        System.out.println("What is Location Latitude? [range from -90,00 to + 90,00] ");
        Double checking;
        do {
            checking = input.nextDouble();
            if (!validator.validateLatitude(String.valueOf(checking))) {
                System.out.println("Wrong data try smth like xx,xx, in range from -90,00 to + 90,00 ");
            }
        } while (!validator.validateLatitude(String.valueOf(checking)));
        return checking;
    }


    public Double takeLocationLongitude() {
        System.out.println("What is Location Longitude? [range from -180,00 to + 180,00]");
        Double checking;
        do {
            checking = input.nextDouble();
            if (!validator.validateLongitude(String.valueOf(checking))) {
                System.out.println("Wrong data try smth like xx,xx, in range from -180,00 to +180,00");
            }
        } while (!validator.validateLongitude(String.valueOf(checking)));
        return checking;
    }

    public CharSequence takeLocalDate() {
        System.out.println("What date you are interested in? Do it in yyyy-mm-dd format");
        CharSequence checking;
        do {
            checking = input.next();
            if (!validator.dateValidation(String.valueOf(checking))) {
                System.out.println("Wrong data try smth like yyyy-mm-dd");
            }
        } while (!validator.dateValidation(String.valueOf(checking)));
        return checking;
    }
}
