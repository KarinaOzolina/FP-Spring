package com.company.factoryprogram_web.data;

import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
                    addAnnotatedClass(UpdatedPartItem.class).
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
    public Storage getStorageData(int id) {
        var session = factory.openSession();
        try {
            return session.get(Storage.class, id);
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



//    public Object orderParts(int configurationId) {
//        var availQty = getAvailableQuantity(configurationId);
//        var requiredQty = getRequiredQuantityForConfiguration(configurationId);
//
//        ArrayList<Integer> resultAvailQuantity = new ArrayList<>();
//
//        for (var i : availQty) {
//            var qty = i.getStorage().getAvailQty();
//            resultAvailQuantity.add(qty);
//        }
//
//        ArrayList<Integer> requiredQuantity = new ArrayList<>();
//
//        for (var i : requiredQty) {
//            int qty = i.getQtyRequired();
//            requiredQuantity.add(qty);
//        }
//
//        Object result;
//        ArrayList<Object> resultArray = new ArrayList<>();
//
//        for (int i = 0; i < resultAvailQuantity.size(); i++) {
//            var avail = resultAvailQuantity.get(i);
//            var req = requiredQuantity.get(i);
//
//            if (avail >= req) {
//                result = avail - req;
//            } else {
//                result = avail;
//            }
//            resultArray.add(result);
//        }
//        return resultArray;
//    }

    public List<Integer> getRequiredQuantity(int configurationId) {
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

    public List<Integer> getAvailableQty(int configurationId) {
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


//    public List<Integer> orderParts(int configurationId) {
//        var storageAvailQty = getAvailableQty(configurationId);
//        var requiredQty = getRequiredQuantity(configurationId);
//
//
//        Integer result;
//        ArrayList<Integer> resultArray = new ArrayList<>();
//
//        for (int i = 0; i < requiredQty.size(); i++) {
//
//            if (storageAvailQty.get(i) >= requiredQty.get(i)) {
//                result =  storageAvailQty.get(i) - requiredQty.get(i);
//            } else {
//                result =  storageAvailQty.get(i);
//            }
//            resultArray.add(result);
//        }
//        return resultArray;
//    }

    public Iterable<UpdatedPartItem> getAvailablePartsAfterUpdate(int configurationId) {
        var session = factory.openSession();

        try {
            var sql = "FROM UpdatedPartItem where configuration_id = :confId";
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



    public void update(@NonNull Object item) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }

}
