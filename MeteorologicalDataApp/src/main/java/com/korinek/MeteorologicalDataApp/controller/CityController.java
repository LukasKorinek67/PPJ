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
@RequestMapping(path="api/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<City> addNewCity(@RequestBody City city){
        try {
            this.cityService.addNewCity(city);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/fill")
    public ResponseEntity<City> addNewCities(@RequestBody List<City> cities){
        try {
            this.cityService.addNewCities(cities);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="{cityId}")
    public ResponseEntity<City> getCity(@PathVariable("cityId") int id){
        try{
            City city = this.cityService.getCity(id);
            return new ResponseEntity<>(city, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities(){
        return new ResponseEntity<>(this.cityService.getAllCities(), HttpStatus.OK);
    }

    @PatchMapping(path="{cityId}")
    public ResponseEntity<City> updateCity(@PathVariable("cityId") int id, @RequestBody City newCity){
        try{
            this.cityService.updateCity(id, newCity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable("cityId") int id){
        try{
            this.cityService.deleteCity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<City> deleteAllCities(){
        this.cityService.deleteAllCities();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
