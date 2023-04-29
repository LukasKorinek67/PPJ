package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.repository.MeasurementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void addNewMeasurement(Measurement measurement) {
        /*if(this.measurementRepository.existsById(measurement.getId()) || this.measurementRepository.existsByName(measurement.getName())){
            throw new DuplicateKeyException("Already exists!");
        } else {
            this.measurementRepository.save(measurement);
        }*/
        throw new NotYetImplementedException();
    }

    public void addNewMeasurements(List<Measurement> measurements) {
        for (Measurement measurement:measurements) {
            this.addNewMeasurement(measurement);
        }
    }

    public Measurement getMeasurement(int id) {
        /*if(this.measurementRepository.existsById(id)){
            Optional<Measurement> measurementOptional = this.measurementRepository.findById(id);
            if (measurementOptional.isPresent()) {
                return measurementOptional.get();
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EntityNotFoundException();
        }*/
        throw new NotYetImplementedException();
    }

    @Transactional(readOnly = true)
    public List<Measurement> getAllMeasurements() {
        //return StreamSupport.stream(measurementRepository.findAll().spliterator(), false).collect(Collectors.toList());
        throw new NotYetImplementedException();
    }

    public void updateMeasurement(int id, Measurement measurement) {
        /*if(this.measurementRepository.existsById(id)){
            measurement.setId(id);
            this.measurementRepository.save(measurement);
        } else {
            throw new EntityNotFoundException();
        }*/
        throw new NotYetImplementedException();
    }

    public void deleteMeasurement(int id) {
        /*if(this.measurementRepository.existsById(id)){
            this.measurementRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }*/
        throw new NotYetImplementedException();
    }

    public void deleteAllMeasurements() {
        //this.measurementRepository.deleteAll();
        throw new NotYetImplementedException();
    }
}
