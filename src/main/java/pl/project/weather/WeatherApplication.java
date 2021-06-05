package pl.project.weather;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.project.weather.Interface.UserInterface;
import pl.project.weather.forecast.ForecastController;
import pl.project.weather.forecast.ForecastService;
import pl.project.weather.location.LocationController;
import pl.project.weather.location.LocationRepositoryImpl;
import pl.project.weather.location.LocationService;

public class WeatherApplication {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();


        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepository);
        LocationController locationController = new LocationController(locationService);

        ForecastService forecastService =  new ForecastService(locationRepository);
        ForecastController forecastController = new ForecastController(forecastService);

        UserInterface userInterface = new UserInterface(locationController, forecastController);
        userInterface.run();
    }
}
