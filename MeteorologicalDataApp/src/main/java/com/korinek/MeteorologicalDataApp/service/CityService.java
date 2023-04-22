package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addNewCity(City city) {
        if(this.cityRepository.existsById(city.getId())){
            throw new DuplicateKeyException("Already exists!");
        } else {
            this.cityRepository.save(city);
        }
    }

    public City getCity(int id) {
        if(this.cityRepository.existsById(id)){
            return this.cityRepository.getReferenceById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }

    public void updateCity(int id, City city) {
        if(this.cityRepository.existsById(id)){
            this.cityRepository.save(city);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteCity(int id) {
        if(this.cityRepository.existsById(id)){
            this.cityRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteAllCities() {
        this.cityRepository.deleteAll();
    }
}
