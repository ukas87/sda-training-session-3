package ui;

import java.util.Scanner;

public class UserInterface {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    static public void start() {
        boolean exit = false;

        do {
            printTerminal();

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    //addLocation();
                    break;
                case 2:
                   // displayCurrentLocation();
                    break;
                case 3:

                   // downloadWeatherValues(); do pobierania danych z serwisu;
                    break;
                case 0:
                    exit = true;
                    System.out.println("Bye, thanks");
                    break;
                default:
                    System.out.println("Wrong data!");
                   // printTerminal();
            }
        } while (!exit);
    }


    static void printTerminal(){
        System.out.println("[1]. Adding a location(to File)");
        System.out.println("[2]. Display currently added locations");
        System.out.println("[3]. Downloading weather values");
        System.out.println("[0]. Exit");
    }

}
