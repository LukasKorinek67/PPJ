package com.korinek.MeteorologicalDataApp.repository;

import com.korinek.MeteorologicalDataApp.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    State findByName(String name);

    boolean existsByName(String name);
}
