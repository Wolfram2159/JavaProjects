package com.company.database.entities;

import com.google.gson.JsonObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private int answer_id;

    @Column(name = "survey_id")
    private int survey_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "rating")
    private int rating;

    public Answer() {
    }

    public Answer(int answer_id, int survey_id, int user_id, int rating) {
        this.answer_id = answer_id;
        this.survey_id = survey_id;
        this.user_id = user_id;
        this.rating = rating;
    }

    public Answer(int answer_id, int rating) {
        this.answer_id = answer_id;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answer_id +
                ", survey_id=" + survey_id +
                ", user_id=" + user_id +
                ", rating=" + rating +
                '}';
    }

    public String toJson(){
        JsonObject json = new JsonObject();
        json.addProperty("answer_id", answer_id);
        json.addProperty("survey_id", survey_id);
        json.addProperty("user_id", user_id);
        json.addProperty("rating", rating);
        return json.toString();
    }

    public JsonObject toJsonObject(){
        JsonObject json = new JsonObject();
        json.addProperty("answer_id", answer_id);
        json.addProperty("survey_id", survey_id);
        json.addProperty("user_id", user_id);
        json.addProperty("rating", rating);
        return json;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
