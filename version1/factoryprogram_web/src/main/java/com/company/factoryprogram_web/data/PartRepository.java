package com.company.factoryprogram_web.data;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

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

    public Iterable<RequiredQuantity> getConfigurationQuantity(int configurationId) {
        var session = factory.openSession();

        try {
//            return session.createQuery("FROM RequiredQuantity").list();
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

//    public Iterable<Storage> getAvailableQuantityInStorage(int partId) {
//        var session = factory.openSession();
//
//        try {
//            var sql = "FROM Storage where part_id = :partId";
//            var query = session.createQuery(sql);
//            query.setParameter("partId", partId);
//            var result = query.list();
//            return result;
//        } catch (HibernateException exception) {
//            System.err.println(exception);
//        } finally {
//            session.close();
//        }
//        return new ArrayList<>();
//    }

//
//    public void compareArrays(int configurationId, int partId) {
//        var qty = getConfigurationQuantity(configurationId);
//        var availQty = getAvailableQuantityInStorage(partId);
//
//        for (int i = 0; i < availQty.size(); i++) {
//            if (availQty.get(i) < qty.get(i)) {
//                System.out.println("Not enough");
//            } else {
//                System.out.println("Enough");
//            }
//        }
//    }




}
