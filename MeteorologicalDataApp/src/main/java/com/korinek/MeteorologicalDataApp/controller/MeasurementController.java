package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping(path=AppPaths.MEASUREMENT_PATH)
    public ResponseEntity<Measurement> addNewMeasurement(@RequestBody Measurement measurement){
        try {
            this.measurementService.addNewMeasurement(measurement);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path=AppPaths.MEASUREMENTS_PATH)
    public ResponseEntity<Measurement> addNewMeasurements(@RequestBody List<Measurement> measurements){
        try {
            this.measurementService.addNewMeasurements(measurements);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path=AppPaths.MEASUREMENT_ID_PATH)
    public ResponseEntity<Measurement> getMeasurement(@PathVariable("id") int id){
        try{
            Measurement measurement = this.measurementService.getMeasurement(id);
            return new ResponseEntity<>(measurement, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path=AppPaths.MEASUREMENTS_PATH)
    public ResponseEntity<List<Measurement>> getAllMeasurements(){
        return new ResponseEntity<>(this.measurementService.getAllMeasurements(), HttpStatus.OK);
    }

    @PatchMapping(path=AppPaths.MEASUREMENT_ID_PATH)
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable("id") int id, @RequestBody Measurement newMeasurement){
        try{
            this.measurementService.updateMeasurement(id, newMeasurement);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path=AppPaths.MEASUREMENT_ID_PATH)
    public ResponseEntity<Measurement> deleteMeasurement(@PathVariable("id") int id){
        try{
            this.measurementService.deleteMeasurement(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path=AppPaths.MEASUREMENTS_PATH)
    public ResponseEntity<Measurement> deleteAllMeasurements(){
        this.measurementService.deleteAllMeasurements();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
