package pl.project.weather.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

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
    public List<String> getAllLocations(){
        List<String> locationsListJsonFormat = null;
        locationService.getAllLocations().forEach(o -> {
            try {
                locationsListJsonFormat.add(objectMapper.writeValueAsString(o));
            } catch (Exception e) {
                locationsListJsonFormat.add("{\"error message\": \"" + e.getMessage() + "\"}");
            }
        });
        return locationsListJsonFormat;
    }
}
