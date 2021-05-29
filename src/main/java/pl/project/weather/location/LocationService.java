package pl.project.weather.location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationService {

    private final LocationRepositoryImpl locationRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationService(LocationRepositoryImpl locationRepository) throws IllegalArgumentException {
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    Location addNewLocation(String cityName, Float longitude, Float latitude, String region, String country) {
        LocationValidator locationValidator = new LocationValidator();  // todo create this class once, it should be a LocationService field
        locationValidator.validateLocation(cityName, longitude, latitude, country);

        Location location = new Location(null, cityName, longitude, latitude,
                locationValidator.validateRegion(region), country);

        return locationRepository.save(location);
    }
}
