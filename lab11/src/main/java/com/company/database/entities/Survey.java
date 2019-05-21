package com.company.database.entities;


import com.google.gson.JsonObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "surveys")
public class Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_id")
    private int survey_id;

    @Column(name = "survey_title")
    private String survey_title;

    @Column(name = "question")
    private String question;

    @Column(name = "user_id")
    private int user_id;

    public Survey() {
    }

    public Survey(int survey_id, String survey_title, String question, int user_id) {
        this.survey_id = survey_id;
        this.survey_title = survey_title;
        this.question = question;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "survey_id=" + survey_id +
                ", survey_title='" + survey_title + '\'' +
                ", question='" + question + '\'' +
                ", user_id=" + user_id +
                '}';
    }
    public String toJson(){
        JsonObject json = new JsonObject();
        json.addProperty("survey_id", survey_id);
        json.addProperty("survey_title", survey_title);
        json.addProperty("question", question);
        json.addProperty("user_id", user_id);
        return json.toString();
    }
    public JsonObject toJsonObject(){
        JsonObject json = new JsonObject();
        json.addProperty("survey_id", survey_id);
        json.addProperty("survey_title", survey_title);
        json.addProperty("question", question);
        json.addProperty("user_id", user_id);
        return json;
    }
    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getSurvey_title() {
        return survey_title;
    }

    public void setSurvey_title(String survey_title) {
        this.survey_title = survey_title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
