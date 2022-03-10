package model.dao;

import model.Weather;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.connection.HibernateUtil;

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

    public void delete(Weather weather){
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

}