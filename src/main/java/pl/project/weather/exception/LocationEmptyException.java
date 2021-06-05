package pl.project.weather.exception;

public class LocationEmptyException extends RuntimeException{

    public LocationEmptyException(String message) {
        super(message);
    }
}
