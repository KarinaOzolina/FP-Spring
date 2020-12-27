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


    // doesn't work
    public Iterable<Object> quantityForChosenConf() {
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

    // doesn't work
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
}
