package pl.project.weather.forecast;

import org.junit.Test;
import pl.project.weather.location.Location;
import pl.project.weather.location.LocationRepositoryMock;

public class ForecastServiceTest {

    @Test
    public void test() {
        LocationRepositoryMock locationRepositoryMock = new LocationRepositoryMock();
        locationRepositoryMock.save(new Location(null,"Cracow",50f,20f," ","Poland"));
        ForecastService forecastService = new ForecastService(locationRepositoryMock);
        Forecast forecast = forecastService.getForecast(1, 1);
        System.out.println(forecast);
    }
}
