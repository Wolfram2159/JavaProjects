package com.company;

import com.company.database.daos.AnswerDao;
import com.company.database.daos.StatsDao;
import com.company.database.daos.SurveyDao;
import com.company.database.daos.UserDao;
import com.company.database.entities.Answer;
import com.company.database.entities.Survey;
import com.company.database.entities.User;
import com.company.database.queries.CreateRecord;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private UserDao userDao = new UserDao();
    private SurveyDao surveyDao = new SurveyDao();
    private AnswerDao answerDao = new AnswerDao();
    private StatsDao statsDao = new StatsDao();

    @RequestMapping("/")
    public String home() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getUsername() != null && userDao.saveUser(user)) {
            return new ResponseEntity<>(user.toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/survey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSurvey(@RequestBody Survey survey, @RequestHeader(name = "user_id") int id) {
        survey.setUser_id(id);
        if (survey.getSurvey_title() != null && survey.getQuestion() != null && surveyDao.saveSurvey(survey)) {
            return new ResponseEntity<>(survey.toJson(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/survey/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserSurveys(@PathVariable int id) {
        List<Survey> surveyList = surveyDao.getUserSurveys(id);
        JsonArray array = new JsonArray();
        for (Survey survey : surveyList) {
            array.add(survey.toJsonObject());
        }
        return new ResponseEntity<>(array.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/survey/", method = RequestMethod.GET)
    public ResponseEntity<String> getAllSurveys() {
        List<Survey> surveyList = surveyDao.getAllSurveys();
        JsonArray array = new JsonArray();
        for (Survey survey : surveyList) {
            array.add(survey.toJsonObject());
        }
        return new ResponseEntity<>(array.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/survey/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSurvey(@PathVariable int id) {
        if (surveyDao.deleteSurvey(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/answer/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAnswer(@RequestBody Answer answer, @RequestHeader(name = "user_id") int id) {
        if(!answerDao.checkIfExist(answer.getSurvey_id(), id)){
            answer.setUser_id(id);
            if (answer.getSurvey_id() != 0 && answer.getRating() >= 0 && answer.getRating() < 6 && answerDao.saveAnswer(answer)) {
                return new ResponseEntity<>(answer.toJson(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/answer/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getAnswersFromSurvey(@PathVariable int id) {
        Answer answer = answerDao.getAnswer(id);
        return new ResponseEntity<>(answer.toJson(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/answer/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAnswer(@PathVariable int id, @RequestBody Answer answer) {
        if(answer.getRating() >= 0 && answer.getRating() < 6) {
            answer = answerDao.updateAnswer(id, answer.getRating());
            if (answer != null) {
                return new ResponseEntity<>(answer.toJson(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/stats/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserStats(@PathVariable int id) {
        List<Survey> surveyList = surveyDao.getUserSurveys(id);
        int a = surveyList.size();
        JsonObject json = new JsonObject();
        json.addProperty("Ilosc", a);
        int i = 0;
        for (Survey survey : surveyList) {
            List<Answer> answerList = answerDao.getAnswers(survey.getSurvey_id());
            JsonObject jsonB = new JsonObject();
            jsonB.addProperty("survey_id", survey.getSurvey_id());
            jsonB.addProperty("Average", calculateAverage(answerList));
            jsonB.addProperty("All", answerList.size());
            json.add("Survey " + i++, jsonB);
        }
        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/stats", method = RequestMethod.GET)
    public ResponseEntity<String> getStats() {
        List<CreateRecord> rankingList = statsDao.surveysRanking();
        JsonObject mainJson = new JsonObject();
        JsonObject rankingPosition = new JsonObject();
        int rank = 0;
        double numberOfSurveys = 0;
        for (CreateRecord record : rankingList) {
            numberOfSurveys += record.getCreate();
            JsonObject userInfo = new JsonObject();
            userInfo.addProperty("user_id", record.getUser_id());
            userInfo.addProperty("username", record.getUsername());
            userInfo.addProperty("createSurveys", record.getCreate());
            rankingPosition.add("" + rank++ + "", userInfo);
        }
        mainJson.add("SurveyRanking", rankingPosition);
        rankingList = statsDao.answersRanking();
        rankingPosition = new JsonObject();
        rank = 0;
        double numberOfAnswers = 0;
        for (CreateRecord record : rankingList) {
            numberOfAnswers += record.getCreate();
            JsonObject userInfo = new JsonObject();
            userInfo.addProperty("user_id", record.getUser_id());
            userInfo.addProperty("username", record.getUsername());
            userInfo.addProperty("createAnswers", record.getCreate());
            rankingPosition.add("" + rank++ + "", userInfo);
        }
        mainJson.add("AnswerRanking", rankingPosition);
        mainJson.addProperty("AverageSurvey", numberOfSurveys / userDao.getNumberOfUsers());
        mainJson.addProperty("AverageAnswer", numberOfAnswers / numberOfSurveys);
        mainJson.addProperty("AllSurveys", numberOfSurveys);
        return new ResponseEntity<>(mainJson.toString(), HttpStatus.OK);
    }

    private double calculateAverage(List<Answer> answerList) {
        if (answerList.size() != 0) {
            double average = 0;
            for (Answer answer : answerList) {
                average += answer.getRating();
            }
            return (average / answerList.size());
        }
        return 0d;
    }
}