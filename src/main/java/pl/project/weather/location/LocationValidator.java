package pl.project.weather.location;

public class LocationValidator {

    public void validateLocation(String cityName, Float longitude, Float latitude, String country) {
        if (cityName.isBlank()) {
            throw new RuntimeException("City name can't be null or empty");
        } else if (longitude > 180 || longitude < -180) {
            throw new IllegalArgumentException("This longitude does not exist");
        } else if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("This latitude does not exist");
        } else if (country.isBlank()) {
            throw new RuntimeException("Country name can't be null or empty");
        }
    }

    public String validateRegion(String region) {
        if(region==null){
            throw new NullPointerException("Region cannot be null");
        }
        if (region.isBlank())
            return null;
        else return region;
    }
}
