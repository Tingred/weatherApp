package pl.project.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.project.weather.location.Location;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "forecasts")
@Builder
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double temperature;
    private Double pressure;
    private Double windSpeed;
    private Double windDegree;
    private Double humidity;

    @ManyToOne
    private Location location;
}
