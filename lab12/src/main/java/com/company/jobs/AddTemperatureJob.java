package com.company.jobs;

import com.company.database.Dao;
import com.company.entities.Location;
import com.company.entities.Temperature;
import com.company.repository.WeatherRepository;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AddTemperatureJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        Location location = (Location) map.get("location");
        Dao dao = (Dao) map.get("dao");
        WeatherRepository repo = (WeatherRepository) map.get("repo");
        Temperature temperature = repo.getTemp(location.getLat(), location.getLon());
        temperature.setLocation_id(location);
        dao.saveTemp(temperature);
    }
}
