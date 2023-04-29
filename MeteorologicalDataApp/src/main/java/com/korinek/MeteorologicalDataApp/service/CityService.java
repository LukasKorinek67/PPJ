package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addNewCity(City city) {
        if(this.cityRepository.existsById(city.getId()) || this.cityRepository.existsByName(city.getName())){
            throw new DuplicateKeyException("Already exists!");
        } else {
            this.cityRepository.save(city);
        }
    }

    public void addNewCities(List<City> cities) {
        for (City city:cities) {
            this.addNewCity(city);
        }
    }

    public City getCity(int id) {
        if(this.cityRepository.existsById(id)){
            Optional<City> cityOptional = this.cityRepository.findById(id);
            if (cityOptional.isPresent()) {
                return cityOptional.get();
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
        //return this.cityRepository.findAll();
    }

    public void updateCity(int id, City city) {
        if(this.cityRepository.existsById(id)){
            city.setId(id);
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
