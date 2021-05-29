package pl.project.weather;

import pl.project.weather.location.LocationController;
import pl.project.weather.location.LocationRepositoryImpl;
import pl.project.weather.location.LocationService;
import pl.project.weather.Interface.UserInterface;

public class WeatherApplication {

    public static void main(String[] args) {
        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl();
        LocationService locationService = new LocationService(locationRepository);
        LocationController locationController = new LocationController(locationService);
        UserInterface userInterface = new UserInterface(locationController);
        userInterface.run();
    }
}
