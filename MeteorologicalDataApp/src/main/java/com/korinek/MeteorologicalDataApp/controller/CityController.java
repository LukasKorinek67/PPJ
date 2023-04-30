package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="api")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(path=AppPaths.CITY_PATH)
    public ResponseEntity<City> addNewCity(@RequestBody City city){
        try {
            this.cityService.addNewCity(city);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path=AppPaths.CITIES_PATH)
    public ResponseEntity<City> addNewCities(@RequestBody List<City> cities){
        try {
            this.cityService.addNewCities(cities);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path=AppPaths.CITY_ID_PATH)
    public ResponseEntity<City> getCity(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(city, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path=AppPaths.CITIES_PATH)
    public ResponseEntity<List<City>> getAllCities(){
        return new ResponseEntity<>(this.cityService.getAllCities(), HttpStatus.OK);
    }

    @PatchMapping(path=AppPaths.CITY_ID_PATH)
    public ResponseEntity<City> updateCity(@PathVariable("id") int id, @RequestBody City newCity){
        try{
            this.cityService.updateCity(id, newCity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path=AppPaths.CITY_ID_PATH)
    public ResponseEntity<City> deleteCity(@PathVariable("id") int id){
        try{
            this.cityService.deleteCity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path=AppPaths.CITIES_PATH)
    public ResponseEntity<City> deleteAllCities(){
        this.cityService.deleteAllCities();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
