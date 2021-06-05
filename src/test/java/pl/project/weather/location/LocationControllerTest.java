package pl.project.weather.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationControllerTest {

    LocationController locationController;
    LocationService locationService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
        locationController = new LocationController(locationService);

    }

    @Test
    public void whenAddNewLocation_givenCorrectValues_thenReturnJsonFormatLocation() throws JsonProcessingException {
        //given
        String cityName = "Poznań";
        Float longitude = 50f;
        Float latitude = 50f;
        String region = "Wielkopolska";
        String country = "Poland";

        //when
        Location serviceResult = locationService.addNewLocation(cityName, longitude, latitude, region, country);
        String controllerResult = locationController.addNewLocation(cityName, longitude, latitude, region, country);
        String jsonFromFingerResult = "{\"id\":1,\"cityName\":\"" + cityName + "\",\"longitude\":" + longitude + ",\"latitude\":" + latitude + ",\"region\":\"" + region + "\",\"country\":\"" + country + "\"}";
        //then
        assertThat(objectMapper.writeValueAsString(serviceResult)).isEqualTo(controllerResult);
        assertThat(jsonFromFingerResult).isEqualTo(controllerResult);
        assertThat(objectMapper.writeValueAsString(serviceResult)).isEqualTo(jsonFromFingerResult);
    }

    @Test
    public void whenAddNewLocation_givenIncorrectCityNameValues_thenThrowsAnException() {
        //when
        String result = locationController.addNewLocation("", 35f, 34f, "region", "Germany");
        String result2 = locationController.addNewLocation(null, 35f, 34f, "region", "Germany");

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "City name can't be null or empty" + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "City name can't be null or empty" + "\"}");

    }

    @Test
    public void whenAddNewLocation_givenIncorrectLongitudeValues_thenThrowsAnException() {
        //when
        String result = locationController.addNewLocation("Berlin", 181f, 34f, "region", "Germany");
        String result2 = locationController.addNewLocation("Berlin", -181f, 34f, "region", "Germany");

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "This longitude does not exist" + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "This longitude does not exist" + "\"}");
    }

    @Test
    public void whenAddNewLocation_givenIncorrectLatitudeValues_thenThrowsAnException() {
        //when
        String result = locationController.addNewLocation("Koln", 35f, 91f, "region", "Germany");
        String result2 = locationController.addNewLocation("Koln", 35f, -91f, "region", "Germany");

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "This latitude does not exist" + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "This latitude does not exist" + "\"}");
    }

    @Test
    public void whenAddNewLocation_givenIncorrectCountryValues_thenThrowsAnException() {
        //when
        String result = locationController.addNewLocation("Berlin", 35f, 34f, "region", "");
        String result2 = locationController.addNewLocation("Berlin", 35f, 34f, "region", null);

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "Country name can't be null or empty" + "\"}");
        assertThat(result2).isEqualTo("{\"error message\": \"" + "Country name can't be null or empty" + "\"}");
    }

    @Test
    public void whenAddNewLocation_givenEmptyOrNullRegionValues_thenReturnJsonFormatLocation() {
        //when
        String result = locationController.addNewLocation("Berlin", 35f, 34f, "", "Germany");
        String result2 = locationController.addNewLocation("Berlin", 35f, 34f, null, "Germany");
        String jsonFromFingerResult = "{\"id\":1,\"cityName\":\"" + "Berlin" + "\",\"longitude\":" + 35f + ",\"latitude\":" + 34f + ",\"region\":" + null + ",\"country\":\"" + "Germany" + "\"}";
        //then
        assertThat(result).isEqualTo(jsonFromFingerResult);
        assertThat(result2).isEqualTo(jsonFromFingerResult);
    }

    @Test
    public void whenGetAllLocations_givenEmptyList_thenReturnJsonFormatException() {
        //when
        String result = locationController.getAllLocations();

        //then
        assertThat(result).isEqualTo("{\"error message\": \"" + "There is no location added" + "\"}");
    }

    @Test
    public void whenGetAllLocations_givenLocationList_thenReturnJsonFormatList(){
        //given
        String result = locationController.addNewLocation("Koblenz", 44.6f, 32.5f, null, "Germany");
        String result2 = locationController.addNewLocation("Hamburg", 35.1f, 34.8f, null, "Germany");

        //when
        String jsonLocations = locationController.getAllLocations();

        //then
        assertThat(jsonLocations).contains(result);
        assertThat(jsonLocations).contains(result2);
    }
}

