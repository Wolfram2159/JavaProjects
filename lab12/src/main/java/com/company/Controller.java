package com.company;

import com.company.database.Dao;
import com.company.entities.LocalizationBody;
import com.company.entities.Location;
import com.company.entities.Temperature;
import com.company.jobs.AddTemperatureJob;
import com.company.repository.WeatherRepository;
import com.google.gson.JsonObject;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Dao dao = new Dao();
    private WeatherRepository repo = new WeatherRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerLocalization(@RequestBody LocalizationBody localization) throws SchedulerException {
        Location location = null;
        if (localization.getFrequency() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (localization.getCityName() == null && (localization.getLat() == null || localization.getLon() == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (localization.getCityName() != null) {
                location = repo.getLocalization(localization.getCityName());
            } else {
                location = repo.getLocalization(localization.getLon(), localization.getLat());
            }
        }

        dao.saveLocation(location);

        JobDataMap map = new JobDataMap();
        map.put("location", location);
        map.put("dao", dao);
        map.put("repo", repo);

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobDetail job = JobBuilder.newJob(AddTemperatureJob.class)
                .usingJobData(map)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(localization.getFrequency())
                        .repeatForever())
                .build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        JsonObject json = new JsonObject();
        json.addProperty("location_id", location.getLocation_id());

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/stat/{id}/{n}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAverageTemp(@PathVariable Integer id, @PathVariable Integer n) {
        JsonObject json = new JsonObject();
        json.addProperty("averageTemp", dao.getAverageTemp(id, n));
        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }
}
