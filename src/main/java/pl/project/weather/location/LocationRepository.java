package pl.project.weather.location;

import java.util.Optional;

public interface LocationRepository {

    Location save(Location location);
    Optional<Location> findById(Integer id);
}
