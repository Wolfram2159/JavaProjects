package com.company.database;

import com.company.entities.Answer;
import com.company.entities.Survey;
import com.company.entities.User;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Dao {
    public Boolean saveUser(User user) {
        Transaction transaction = null;
        try (Session session = Database.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
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
    public Boolean saveSurvey(Survey survey){
        Transaction transaction = null;
        try (Session session = Database.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(survey);
            // commit transaction
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
    public List<Survey> getUserSurveys(int id){
        try (Session session = Database.getSessionFactory().openSession()) {
            SQLQuery q = session.createSQLQuery("select * from SURVEYS where user_id = " + id);
            q.addEntity(Survey.class);
            return q.list();
        }
    }
    public List<Survey> getAllSurveys(){
        try (Session session = Database.getSessionFactory().openSession()) {
            SQLQuery q = session.createSQLQuery("select * from SURVEYS");
            q.addEntity(Survey.class);
            return q.list();
        }
    }
    public Boolean deleteSurvey(int id){
        Transaction transaction = null;
        try (Session session = Database.getSessionFactory().openSession()) {
            // start a transaction
            SQLQuery q = session.createSQLQuery("select * from SURVEYS where survey_id = " + id);
            q.addEntity(Survey.class);
            Survey survey = (Survey) q.getSingleResult();
            transaction = session.beginTransaction();
            // save the student object
            session.delete(survey);
            // commit transaction
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
    public Boolean saveAnswer(Answer answer){
        Transaction transaction = null;
        try (Session session = Database.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(answer);
            // commit transaction
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
    public Answer getAnswer(int id){
        try (Session session = Database.getSessionFactory().openSession()) {
            SQLQuery q = session.createSQLQuery("select * from ANSWERS where answer_id = " + id);
            q.addEntity(Answer.class);
            return (Answer) q.getSingleResult();
        }
    }
    public List<Answer> getAnswers(int id){
        try (Session session = Database.getSessionFactory().openSession()) {
            SQLQuery q = session.createSQLQuery("select * from ANSWERS where survey_id = " + id);
            q.addEntity(Answer.class);
            return q.list();
        }
    }
    public Answer updateAnswer(int id, int rating){
        Answer ans = getAnswer(id);
        ans.setRating(rating);
        Transaction transaction = null;
        try (Session session = Database.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(ans);
            // commit transaction
            transaction.commit();
            return ans;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
}
