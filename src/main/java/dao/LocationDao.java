package dao;
import model.Location;
import model.Weather;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.connection.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {
    public void save(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(location);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }

    }

    public void delete(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(location);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public Location findByCity(String city) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Location location = session.createNativeQuery("""
                            SELECT *
                            FROM locations
                            WHERE city_name = :cityName""", Location.class)
                    .setParameter("cityName", city)
                    .uniqueResult();

            transaction.commit();
            return location;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        return null;
    }

    public Location findByCityAndCountry(String city, String country) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Location location = session.createQuery("FROM Location WHERE cityName = :city AND countryName = :country", Location.class)
                    .setParameter("city", city)
                    .setParameter("country", country)
                    .uniqueResult();

            transaction.commit();
            return location;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteAllLocations() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("""
                    DELETE  From locations
                    """
            ).executeUpdate();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public void update(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(location);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public void saveWeather(Weather weather) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(weather);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public List<Location> findAll() {
        Transaction transaction = null;
        List<Location> locations = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            locations =
                    session.createQuery("FROM Location ", Location.class).getResultList();

            transaction.commit();
            return locations;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }

        return locations;
    }


}
