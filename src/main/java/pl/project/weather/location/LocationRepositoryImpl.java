package pl.project.weather.location;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class LocationRepositoryImpl implements LocationRepository {

    private final SessionFactory sessionFactory;

    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        return location;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        Session session = sessionFactory.openSession();
        Location location = session.createQuery("FROM locations l WHERE l.id = :id", Location.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return Optional.ofNullable(location);
    }
}
