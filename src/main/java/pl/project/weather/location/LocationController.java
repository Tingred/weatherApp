package pl.project.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationController {

    private final LocationService locationService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    public String addNewLocation(String cityName, Float longitude, Float latitude, String region, String country) {
        try {
            Location newLocation = locationService.addNewLocation(cityName, longitude,
                    latitude, region, country);
            return objectMapper.writeValueAsString(newLocation);
        } catch (Exception e) {
            return "{\"error message\": \"" + e.getMessage() + "\"}";
        }
    }

    public String getAllLocations() {
        try {
            return objectMapper.writeValueAsString(locationService.getAllLocations());
        } catch (Exception e) {
            return "{\"error message\": \"" + e.getMessage() + "\"}";
        }
    }
}
