package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.Measurement;
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
public class LatestWeatherController {

    private final CityService cityService;
    private final MeasurementService measurementService;

    @Autowired
    public LatestWeatherController(CityService cityService, MeasurementService measurementService) {
        this.cityService = cityService;
        this.measurementService = measurementService;
    }

    @GetMapping(path=AppPaths.LATEST_WEATHER)
    public ResponseEntity<Measurement> getLatestWeather(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(measurementService.getLatestMeasurement(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>("Latest Weather - city with id: " + id, HttpStatus.OK);
    }
}
