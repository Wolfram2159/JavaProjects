package com.company.database;

import com.company.entities.Location;
import com.company.entities.Temperature;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Dao {
    private Session session;

    public Dao() {
        session = Database.getSessionFactory().openSession();
    }

    public void saveLocation(Location location) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveTemp(Temperature temperature){
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(temperature);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public Double getAverageTemp(Integer id, Integer n){
        SQLQuery q = session.createSQLQuery("SELECT AVG(temperature) FROM (SELECT * FROM temperatures WHERE location_id=" + id + " ORDER BY temp_id DESC LIMIT " + n + ") AS TEMP");
        return (Double) q.getSingleResult();
    }
}
