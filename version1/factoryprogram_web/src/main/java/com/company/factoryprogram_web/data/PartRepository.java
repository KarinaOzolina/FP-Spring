package com.company.factoryprogram_web.data;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class PartRepository {

    private static SessionFactory factory;

    public PartRepository() {
        try {
            factory = new org.hibernate.cfg.Configuration().
                    configure().
                    addAnnotatedClass(Part.class).
                    addAnnotatedClass(Configuration.class).
                    addAnnotatedClass(Storage.class).
                    addAnnotatedClass(RequiredQuantity.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Part> getParts() {
        var session = factory.openSession();
        try {
            return session.createQuery("FROM Part").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Iterable<Configuration> getConfigurations() {
        var session = factory.openSession();
        try {
            return session.createQuery("FROM Configuration").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Configuration getConfiguration(int id) {
        var session = factory.openSession();
        try {
            return session.get(Configuration.class, id);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return null;
    }

    // Returns required quantity of parts for chosen configuration
    public Iterable<RequiredQuantity> getRequiredQuantityForConfiguration(int configurationId) {
        var session = factory.openSession();

        try {
            var sql = "FROM RequiredQuantity where configuration_id = :confId";
            var query = session.createQuery(sql);
            query.setParameter("confId", configurationId);
            var result = query.list();
            return result;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Iterable<RequiredQuantity> getAvailableQuantity(int configurationId) {
        var session = factory.openSession();

        try {
            var sql = "FROM RequiredQuantity where configuration_id = :confId";
            var query = session.createQuery(sql);
            query.setParameter("confId", configurationId);
            var result = query.list();
            return result;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Iterable<Object> getAvailabilityOfParts(int configurationId) {
        var availQty = getAvailableQuantity(configurationId);
        var requiredQty = getRequiredQuantityForConfiguration(configurationId);

        ArrayList<Integer> resultAvailQuantity = new ArrayList<>();

        for (var i : availQty) {
            int qty = i.getStorage().getAvailQty();
            resultAvailQuantity.add(qty);
        }

        ArrayList<Integer> requiredQuantity = new ArrayList<>();

        for (var i : requiredQty) {
            int qty = i.getQtyRequired();
            requiredQuantity.add(qty);
        }

        Object result;
        ArrayList<Object> resultArray = new ArrayList<>();

        for (int i = 0; i < resultAvailQuantity.size(); i++) {
            if (resultAvailQuantity.get(i) < requiredQuantity.get(i)) {
                result = "Not enough";

            } else {
                result = "Enough";
            }
            resultArray.add(result);
        }
        return resultArray;
    }

}
