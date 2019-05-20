package com.company;

import com.company.database.Dao;
import com.company.entities.Answer;
import com.company.entities.Survey;
import com.company.entities.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    Dao dao = new Dao();
    User lastUser;

    @RequestMapping("/")
    public String home() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (dao.saveUser(user)) {
            lastUser = user;
            return new ResponseEntity<>(user.toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/survey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSurvey(@RequestBody Survey survey) {
        survey.setUser_id(lastUser.getUser_id());
        if (dao.saveSurvey(survey)) {
            return new ResponseEntity<>(survey.toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/survey/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserSurveys(@PathVariable int id) {
        List<Survey> surveyList = dao.getUserSurveys(id);
        JsonArray array = new JsonArray();
        for (Survey survey : surveyList) {
            array.add(survey.toJsonObject());
        }
        return new ResponseEntity<>(array.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/survey/", method = RequestMethod.GET)
    public ResponseEntity<String> getAllSurveys() {
        List<Survey> surveyList = dao.getAllSurveys();
        JsonArray array = new JsonArray();
        for (Survey survey : surveyList) {
            array.add(survey.toJsonObject());
        }
        return new ResponseEntity<>(array.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/survey/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSurvey(@PathVariable int id) {
        if (dao.deleteSurvey(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/answer/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAnswer(@RequestBody Answer answer) {
        answer.setUser_id(lastUser.getUser_id());
        if (dao.saveAnswer(answer)) {
            return new ResponseEntity<>(answer.toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/answer/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getAnswersFromSurvey(@PathVariable int id) {
        Answer answer = dao.getAnswer(id);
        return new ResponseEntity<>(answer.toJson(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/answer/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAnswer(@PathVariable int id, @RequestBody Answer answer) {
        answer = dao.updateAnswer(id, answer.getRating());
        if (answer != null) {
            return new ResponseEntity<>(dao.getAnswer(id).toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/stats/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserStats(@PathVariable int id) {
        List<Survey> surveyList = dao.getUserSurveys(id);
        int a = surveyList.size();
        System.out.println("a = " + a);
        JsonObject json = new JsonObject();
        json.addProperty("ilosc", a);
        for (Survey survey : surveyList) {
            List<Answer> answerList = dao.getAnswers(survey.getSurvey_id());
            JsonObject jsonB = new JsonObject();
            jsonB.addProperty("id", survey.getSurvey_id());
            jsonB.addProperty("average", calculateAverage(answerList));
            jsonB.addProperty("all", answerList.size());
            json.add("id" + survey.getSurvey_id(), jsonB);
        }
        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

    public double calculateAverage(List<Answer> answerList) {
        if (answerList.size() !=0) {
            double average = 0;
            for (Answer answer : answerList) {
                average += answer.getRating();
            }
            return (average / answerList.size());
        }
        return 0d;
    }
}