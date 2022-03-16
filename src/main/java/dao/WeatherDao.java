package dao;

import model.Weather;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.connection.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherDao {

    public void save(Weather weather) {

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

    public void delete(Weather weather) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(weather);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public Weather findById(Integer id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Weather weather = session.get(Weather.class, id);

            transaction.commit();

            return weather;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        return null;
    }


    public void update(Weather weather) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(weather);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null)
                transaction.rollback();
        }

    }

    public void deleteAllWeathers() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("""
                    DELETE  From weathers
                    """
            ).executeUpdate();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public List<Weather> getWeatherByDateAndCity(LocalDate date, String city) {
        Transaction transaction = null;
        List<Weather> weathers = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            weathers = session.createNativeQuery("""
                            SELECT *
                            FROM weathers
                            JOIN locations using(location_id)
                            WHERE date = :date AND city_name = :city
                            """, Weather.class)
                    .setParameter("date", date)
                    .setParameter("city", city)
                    .getResultList();

            transaction.commit();
            return weathers;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        return weathers;
    }
}