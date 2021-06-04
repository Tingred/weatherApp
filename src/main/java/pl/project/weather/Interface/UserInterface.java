package pl.project.weather.Interface;

import pl.project.weather.forecast.ForecastController;
import pl.project.weather.location.LocationController;

import java.util.Scanner;

public class UserInterface {

    private final LocationController locationController;
    private final ForecastController forecastController;

    public UserInterface(LocationController locationController, ForecastController forecastController) {
        this.locationController = locationController;
        this.forecastController = forecastController;
    }

    public void run() {
        System.out.println("Application is running!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome in weather application, what you can do is:");
            System.out.println("1. Add new location");
            System.out.println("2. Get weather forecast");
            System.out.println("0. Exit");

            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    getWeatherForecast();
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
        Float longitude = scanner.nextFloat();
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
                .addNewLocation(cityName, longitude, latitude,
                        region, country);
        System.out.println("Server response: " + httpResponseBody);
        System.out.println();
    }

    private void getWeatherForecast() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number of date to get weather forecast: \n" +
                "0. Today\n" +
                "1. Tomorrow\n" +
                "2. Overmorrow\n" +
                "Next days: 3; 4; 5; 6; 7.");
        int userNumber = scanner.nextInt();
        scanner.nextLine();
        Integer date;
        if (userNumber == 1 || userNumber == 2 || userNumber == 3 || userNumber == 4 || userNumber == 5 || userNumber == 6 || userNumber == 7){
            date = userNumber;
        }else date = 1;
        System.out.println("Write id of location from you want weather forecast:");
        Integer locationId = scanner.nextInt();
        String httpResponseBody = forecastController
                .getWeatherForecast(locationId, date);
        System.out.println("Server response: " + httpResponseBody);
        System.out.println();




    }
}
