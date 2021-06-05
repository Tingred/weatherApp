package pl.project.weather.location;

import pl.project.weather.exception.CollectionEmptyException;

import java.util.List;

public class LocationValidator {

    public void validateLocation(String cityName, Float longitude, Float latitude, String country) {
        if (cityName == null || cityName.isBlank()) {
            throw new RuntimeException("City name can't be null or empty");
        } else if (longitude > 180 || longitude < -180) {
            throw new IllegalArgumentException("This longitude does not exist");
        } else if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("This latitude does not exist");
        } else if (country == null || country.isBlank()) {
            throw new RuntimeException("Country name can't be null or empty");
        }
    }

    public String validateRegion(String region) {
        if (region == null || region.isBlank())
            return null;
        else return region;
    }

    public List<Location> validateLocationList(List<Location> locations) {
        if (locations.isEmpty()) {
            throw new CollectionEmptyException("There is no location added");
        }
        return locations;
    }
}
