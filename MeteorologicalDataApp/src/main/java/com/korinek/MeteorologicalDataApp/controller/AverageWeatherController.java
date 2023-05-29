package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.WeatherAverages;
import com.korinek.MeteorologicalDataApp.service.CityService;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api")
public class AverageWeatherController {

    private final CityService cityService;
    private final MeasurementService measurementService;

    @Autowired
    public AverageWeatherController(CityService cityService, MeasurementService measurementService) {
        this.cityService = cityService;
        this.measurementService = measurementService;
    }

    @GetMapping(path=AppPaths.AVERAGE_WEATHER_LAST_DAY)
    public ResponseEntity<WeatherAverages> getAverageWeatherLastDay(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(measurementService.getAverageWeatherLastDay(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>("Average Weather for last day - city with id: " + id, HttpStatus.OK);
    }

    @GetMapping(path=AppPaths.AVERAGE_WEATHER_LAST_WEEK)
    public ResponseEntity<WeatherAverages> getAverageWeatherLastWeek(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(measurementService.getAverageWeatherLastWeek(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>("Average Weather for last week - city with id: " + id, HttpStatus.OK);
    }

    @GetMapping(path=AppPaths.AVERAGE_WEATHER_LAST_14_DAYS)
    public ResponseEntity<WeatherAverages> getAverageWeatherLast14Days(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(measurementService.getAverageWeatherLast14Days(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>("Average Weather for last 14 days - city with id: " + id, HttpStatus.OK);
    }
}
