package com.company.database.daos;

import com.company.database.Database;
import com.company.database.entities.User;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDao {
    private Session session;

    public UserDao() {
        session = Database.getSessionFactory().openSession();
    }

    public Boolean saveUser(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public double getNumberOfUsers() {
        SQLQuery q = session.createSQLQuery("select * from users");
        q.addEntity(User.class);
        return q.list().size();
    }
}
