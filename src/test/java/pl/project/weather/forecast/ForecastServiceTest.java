package pl.project.weather.forecast;

import org.junit.Test;
import pl.project.weather.exception.LocationEmptyException;
import pl.project.weather.location.Location;
import pl.project.weather.location.LocationRepository;
import pl.project.weather.location.LocationRepositoryMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class ForecastServiceTest {

    @Test
    public void whenGetForecast_givenHappyPathValues_thenGetForecastSucceed() {

        LocationRepository locationRepository = new LocationRepositoryMock();
        ForecastService forecastService = new ForecastService(locationRepository);
        Location location = locationRepository.save(new Location(null, "Cracow", 50f, 20f, " ", "Poland"));
        //when
        //given
        Forecast result = forecastService.getForecast(1, 1);
        //then
        assertThat(result).isNotEqualTo(new RuntimeException("Forecast for the past value has not been found"));
        assertThat(result).isNotEqualTo(new LocationEmptyException("There is no location with id " + location.getId()));
    }

    @Test
    public void whenGetForecast_givenWrongLocationId_thenThrowsAnException() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        ForecastService forecastService = new ForecastService(locationRepository);
        Location location = locationRepository.save(new Location(null, "Cracow", 50f, 20f, " ", "Poland"));
        //when
        //given
        Integer locationId = 2;
        Integer locationId2 = -2;
        Throwable result = catchThrowable(()->forecastService.getForecast(locationId, 1));
        Throwable result2 = catchThrowable(()->forecastService.getForecast(locationId2, 1));
        //then
        assertThat(result).isExactlyInstanceOf(LocationEmptyException.class);
        assertThat(result2).isExactlyInstanceOf(LocationEmptyException.class);
    }
    @Test
    public void whenGetForecast_givenWrongDate_thenThrowsAnException(){

        LocationRepository locationRepository = new LocationRepositoryMock();
        ForecastService forecastService = new ForecastService(locationRepository);
        Location location = locationRepository.save(new Location(null, "Cracow", 50f, 20f, " ", "Poland"));
        //when
        //given
        Integer date1 = 8;
        Integer date2 = -2;
        Throwable result = catchThrowable(()->forecastService.getForecast(1, date1));
        Throwable result2 = catchThrowable(()->forecastService.getForecast(1, date2));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
        assertThat(result2).isExactlyInstanceOf(RuntimeException.class);
    }
}
