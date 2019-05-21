package com.company.database.daos;

import com.company.database.Database;
import com.company.database.entities.Answer;
import com.company.database.entities.Survey;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SurveyDao {
    private Session session;

    public SurveyDao() {
        session = Database.getSessionFactory().openSession();
    }

    public Boolean saveSurvey(Survey survey) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(survey);
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

    public List<Survey> getUserSurveys(int id) {
        SQLQuery q = session.createSQLQuery("select * from SURVEYS where user_id = " + id);
        q.addEntity(Survey.class);
        return q.list();
    }

    public List<Survey> getAllSurveys() {
        SQLQuery q = session.createSQLQuery("select * from SURVEYS");
        q.addEntity(Survey.class);
        return q.list();

    }

    public Boolean deleteSurvey(int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            SQLQuery deleteAnswers = session.createSQLQuery("select * from ANSWERS where survey_id = " + id);
            deleteAnswers.addEntity(Answer.class);
            List<Answer> answerList = deleteAnswers.list();
            for (Answer answer : answerList) {
                session.delete(answer);
            }
            SQLQuery deleteSurvey = session.createSQLQuery("select * from SURVEYS where survey_id = " + id);
            deleteSurvey.addEntity(Survey.class);
            Survey survey = (Survey) deleteSurvey.getSingleResult();
            session.delete(survey);

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
}
