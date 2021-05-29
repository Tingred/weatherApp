package pl.project.weather.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;
}
