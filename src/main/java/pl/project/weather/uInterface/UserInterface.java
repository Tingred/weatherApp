package pl.project.weather.uInterface;

import pl.project.weather.location.LocationController;

import java.util.Scanner;

public class UserInterface {

    private final LocationController locationController;

    public UserInterface(LocationController locationController) {
        this.locationController = locationController;
    }

    public void run() {
        System.out.println("Application is running!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome in weather application, what you can do is:");
            System.out.println("1. Add new location");
            System.out.println("0. Exit");

            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write city name:");
        String cityName = scanner.nextLine();
        System.out.println("Write longitude:");
        Float longtitude = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Write latitude:");
        Float latitude = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Do you want to write region? (Y - yes; N - no)");
        String region = null;
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("Y")) {
                System.out.println("Write region:");
                region = scanner.nextLine();
                break;
            } else if (response.equals("N"))
                break;
        }
        System.out.println("Write country:");
        String country = scanner.nextLine();
        System.out.println();

        String httpResponseBody = locationController
                .addNewLocation(cityName,longtitude,latitude,
                        region, country);
        System.out.println("Server response: "+httpResponseBody);
        System.out.println();
    }
}
