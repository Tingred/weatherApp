package pl.project.weather.location;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LocationServiceTest {

    LocationService locationService;

    @Before
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    public void whenAddNewLocation_givenCorrectValues_thenAddsNewLocation() {
        //when
        Location result = locationService.addNewLocation("Poznań", 50.5f, 25.35f, "Wielkopolska", "Poland");

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCityName()).isEqualTo("Poznań");
        assertThat(result.getCountry()).isEqualTo("Poland");
        assertThat(result.getLatitude()).isEqualTo(25.35f);
        assertThat(result.getLongitude()).isEqualTo(50.5f);
        assertThat(result.getRegion()).isEqualTo("Wielkopolska");
    }

    @Test
    public void whenAddNewLocation_givenCountryIsNullOrEmpty_thenThrowsAnException(){
        //when
        Throwable result = catchThrowable(()-> locationService.addNewLocation("Poznań", 35f, 34f, "region", ""));
        Throwable result2 = catchThrowable(()-> locationService.addNewLocation("Poznań", 35f, 34f, "region", null));

        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
        assertThat(result2).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenAddNewLocation_givenCityNameIsNullOrEmpty_thenThrowsAnException(){
        //when
        Throwable result = catchThrowable(()-> locationService.addNewLocation(null, 35f, 34f, "region", "country"));
        Throwable result2 = catchThrowable(()-> locationService.addNewLocation("", 35f, 34f, "region", "country"));

        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
        assertThat(result2).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenAddNewLocation_givenLongitudeIsUnreal_thenThrowsAnException(){
        //when
        Throwable result = catchThrowable(()-> locationService.addNewLocation("Poznań", 182f, 34f, "region", "Poland"));
        Throwable result2 = catchThrowable(()-> locationService.addNewLocation("Poznań", -182f, 34f, "region", "Poland"));
        Throwable result3 = catchThrowable(()-> locationService.addNewLocation("Poznań", 181f, 34f, "region", "Poland"));
        Throwable result4 = catchThrowable(()-> locationService.addNewLocation("Poznań", -181f, 34f, "region", "Poland"));

        //then
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result2).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result3).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result4).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddNewLocation_givenLatitudeIsUnreal_thenThrowsAnException(){
        //when
        Throwable result = catchThrowable(()-> locationService.addNewLocation("Poznań", 179f, 92f, "region", "Poland"));
        Throwable result2 = catchThrowable(()-> locationService.addNewLocation("Poznań", -179f, 91f, "region", "Poland"));
        Throwable result3 = catchThrowable(()-> locationService.addNewLocation("Poznań", 178f, -92f, "region", "Poland"));
        Throwable result4 = catchThrowable(()-> locationService.addNewLocation("Poznań", -178f, -91f, "region", "Poland"));

        //then
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result2).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result3).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(result4).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddNewLocation_givenRegionNullOrEmpty_thenReturnNull(){
        //when
        Location location = locationService.addNewLocation("Wrocław", 35f, 34f, "", "country");
        Location location2 = locationService.addNewLocation("Wrocław", 35f, 34f, null, "country");

        //then
        assertThat(location.getRegion()).isNull();
        assertThat(location2.getRegion()).isNull();
    }
}
