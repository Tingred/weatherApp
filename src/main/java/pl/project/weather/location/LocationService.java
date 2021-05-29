package pl.project.weather.location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final LocationValidator locationValidator = new LocationValidator();

    public LocationService(LocationRepository locationRepository) throws IllegalArgumentException {
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    Location addNewLocation(String cityName, Float longitude, Float latitude, String region, String country) {
        locationValidator.validateLocation(cityName, longitude, latitude, country);
        Location location = new Location(null, cityName, longitude, latitude,
                locationValidator.validateRegion(region), country);

        return locationRepository.save(location);
    }
    List<Location> getAllLocations(){
        return locationRepository.getAll();
    }
}
