package com.company.database.daos;

import com.company.database.Database;
import com.company.database.queries.CreateRecord;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StatsDao {
    private Session session;

    public StatsDao() {
        session = Database.getSessionFactory().openSession();
    }
    public List<CreateRecord> surveysRanking() {
        SQLQuery q = session.createSQLQuery("SELECT " +
                "users.user_id, users.username, COUNT(surveys.survey_id) AS createSurveys " +
                "FROM surveys " +
                "INNER JOIN users " +
                "ON surveys.user_id = users.user_id " +
                "GROUP BY user_id " +
                "ORDER BY createSurveys DESC");
        List<Object[]> results = q.getResultList();
        List<CreateRecord> createRecordList = new ArrayList<>();

        results.forEach(
                (record) -> {
                    int a1 = (Integer) record[0];
                    String a2 = (String) record[1];
                    BigInteger a3 = (BigInteger) record[2];
                    createRecordList.add(new CreateRecord(
                            a1,
                            a2,
                            a3.intValue()));
                }
        );
        return createRecordList;

    }

    public List<CreateRecord> answersRanking() {
        SQLQuery q = session.createSQLQuery("SELECT " +
                "users.user_id, users.username, COUNT(answers.answer_id) AS createAnswers " +
                "FROM answers  " +
                "INNER JOIN users " +
                "ON answers.user_id = users.user_id " +
                "GROUP BY user_id " +
                "ORDER BY createAnswers  DESC");
        List<Object[]> results = q.getResultList();
        List<CreateRecord> createRecordList = new ArrayList<>();

        results.forEach(
                (record) -> {
                    int a1 = (Integer) record[0];
                    String a2 = (String) record[1];
                    BigInteger a3 = (BigInteger) record[2];
                    createRecordList.add(new CreateRecord(
                            a1,
                            a2,
                            a3.intValue()));
                }
        );
        return createRecordList;
    }
}
