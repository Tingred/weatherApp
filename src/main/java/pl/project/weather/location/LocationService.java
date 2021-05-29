package pl.project.weather.location;

public class LocationService {

    private final LocationRepositoryImpl locationRepository;


    public LocationService(LocationRepositoryImpl locationRepository) throws IllegalArgumentException{
        this.locationRepository = locationRepository;
    }
    Location addNewLocation(String cityName, Float logitude, Float latitude, String region, String country) {
        LocationValidator locationValidator = new LocationValidator();
        locationValidator.validateLocation(cityName, logitude, latitude, country);

        Location location = new Location(null,cityName, logitude, latitude,
                locationValidator.validateRegion(region), country);

        return  locationRepository.save(location);
    }
}
