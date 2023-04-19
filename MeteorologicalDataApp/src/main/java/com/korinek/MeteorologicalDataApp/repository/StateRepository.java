package com.korinek.MeteorologicalDataApp.repository;

import com.korinek.MeteorologicalDataApp.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
