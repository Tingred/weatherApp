package pl.project.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class ForecastController {

    private final ForecastService forecastService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    public String getWeatherForecast(Integer locationId, Integer date){
        try {
            Forecast forecast = forecastService.getForecast(locationId,date);
            return objectMapper.writeValueAsString(forecast);
        } catch (Exception e) {
            return "{\"error message\": \"" + e.getMessage() + "\"}";
        }
    }
}
