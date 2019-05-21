package com.company.database.daos;

import com.company.database.Database;
import com.company.database.entities.Answer;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnswerDao {
    private Session session;

    public AnswerDao() {
        session = Database.getSessionFactory().openSession();
    }

    public Boolean saveAnswer(Answer answer) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(answer);
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

    public Answer getAnswer(int id) {
        SQLQuery q = session.createSQLQuery("select * from ANSWERS where answer_id = " + id);
        q.addEntity(Answer.class);
        return (Answer) q.getSingleResult();
    }

    public List<Answer> getAnswers(int id) {
        SQLQuery q = session.createSQLQuery("select * from ANSWERS where survey_id = " + id);
        q.addEntity(Answer.class);
        return q.list();
    }

    public Answer updateAnswer(int id, int rating) {
        Answer ans = getAnswer(id);
        ans.setRating(rating);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(ans);
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

    public Boolean checkIfExist(int survey_id, int user_id){
        SQLQuery q = session.createSQLQuery("select * from ANSWERS");
        q.addEntity(Answer.class);
        List<Answer> answerList = q.list();
        for (Answer answer : answerList) {
            if (answer.getSurvey_id() == survey_id && answer.getUser_id() == user_id){
                return true;
            }
        }
        return false;
    }
}
