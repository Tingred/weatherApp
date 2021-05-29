package pl.project.weather.location;

public class LocationValidator {

    public void validateLocation(String cityName, Float logitude, Float latitude, String country) {
        if (cityName.isBlank()) { // todo NullPointerException may occur
            throw new IllegalArgumentException("Nazwa miasta nie może być pusta");
        } else if (logitude > 180 || logitude < -180) {
            throw new IllegalArgumentException("Taka długość geograficzna nie istnieje");
        } else if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("Taka szerokość geograficzna nie istnieje");
        } else if (country.isBlank()) { // todo NullPointerException may occur
            throw new IllegalArgumentException("Nazwa kraju nie może być pusta");
        }
    }

    public String validateRegion(String region) {
        if (region.isBlank()) // todo NullPointerException may occur
            return null;
        else return region;
    }
}
