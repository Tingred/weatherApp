package pl.project.weather.forecast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pl.project.weather.location.Location;
import pl.project.weather.location.LocationRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@RequiredArgsConstructor
public class ForecastService {

    final private LocationRepository locationRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Forecast getForecast(Integer locationId, Integer date) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("There is no location with id " + locationId));


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&exclude=minutely,hourly&appid=1766fdc82c622688913c1bb885b9bd94"))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ForecastResponseDTO forecastResponseDTO = objectMapper.readValue(responseBody, ForecastResponseDTO.class);

            LocalDate forcastDate = LocalDate.now().plusDays(date);

            Forecast forecast = forecastResponseDTO.getDaily().stream()
                    .filter(s -> s.getDate().equals(forcastDate))
                    .map(s -> Forecast.builder()
                            .temperature(s.getTemperature().getCelsius())
                            .pressure(s.getPressure())
                            .windSpeed(s.getWindSpeed())
                            .windDegree(s.getWindDegree())
                            .humidity(s.getHumidity()).location(location)
                            .build())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Forecast for the past value has not been found"));
            return forecast;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
