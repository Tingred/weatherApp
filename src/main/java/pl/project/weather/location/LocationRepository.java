package pl.project.weather.location;

import java.util.List;

public interface LocationRepository {

    Location save(Location location);
    List<Location> getAll();
}
