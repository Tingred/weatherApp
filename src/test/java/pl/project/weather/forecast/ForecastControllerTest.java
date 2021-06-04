package pl.project.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import pl.project.weather.location.Location;
import pl.project.weather.location.LocationRepository;
import pl.project.weather.location.LocationRepositoryMock;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastControllerTest {

    ForecastController controller;
    ForecastService forecastService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        forecastService = new ForecastService(locationRepository);
        controller = new ForecastController(forecastService);
        locationRepository.save(new Location(null, "Cracow", 50f, 20f, " ", "Poland"));
    }

    @Test
    public void whenGetForecast_givenIncorrectDataValue_thenThrowsAnException() {
        //when
        String result = controller.getWeatherForecast(1, -1);
        String result2 = controller.getWeatherForecast(1, 10);
        String result3 = controller.getWeatherForecast(1, 9);

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "Forecast for the past value has not been found" + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "Forecast for the past value has not been found" + "\"}");
        assertThat(result3).isEqualTo("{\"error message\": \"" + "Forecast for the past value has not been found" + "\"}");
    }

    @Test
    public void whenGetForecast_givenIncorrectLocationValue_thenThrowsAnException() {
        //when
        String result = controller.getWeatherForecast(2, 0);
        String result2 = controller.getWeatherForecast(0, 5);
        String result3 = controller.getWeatherForecast(-1, 2);

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "There is no location with id "+(2) + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "There is no location with id " +(0)+ "\"}");
        assertThat(result3).isEqualTo("{\"error message\": \"" + "There is no location with id " +(-1)+ "\"}");
    }
}