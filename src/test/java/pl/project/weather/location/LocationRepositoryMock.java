package pl.project.weather.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryMock implements LocationRepository {

    private List<Location> locations = new ArrayList<>();

    @Override
    public Location save(Location location) {
        location.setId(1);
        locations.add(location);
        return location;

    }

    @Override
    public List<Location> getAll() {
        return locations;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return locations.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public void clear(){}
}
