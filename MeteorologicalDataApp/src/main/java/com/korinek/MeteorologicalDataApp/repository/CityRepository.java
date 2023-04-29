package com.korinek.MeteorologicalDataApp.repository;

import com.korinek.MeteorologicalDataApp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String name);

    boolean existsByName(String name);
}
