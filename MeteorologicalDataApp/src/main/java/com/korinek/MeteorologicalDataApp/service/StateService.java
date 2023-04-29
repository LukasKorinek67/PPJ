package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.State;
import com.korinek.MeteorologicalDataApp.repository.StateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    public void addNewState(State state) {
        if(this.stateRepository.existsById(state.getId()) || this.stateRepository.existsByName(state.getName())){
            throw new DuplicateKeyException("Already exists!");
        } else {
            this.stateRepository.save(state);
            System.out.println("--------------------");
            System.out.println(state.getCities().size());
            System.out.println("--------------------");
        }
    }

    public void addNewStates(List<State> states) {
        for (State state:states) {
            this.addNewState(state);
        }
    }

    public State getState(int id) {
        if(this.stateRepository.existsById(id)){
            Optional<State> stateOptional = this.stateRepository.findById(id);
            if (stateOptional.isPresent()) {
                return stateOptional.get();
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        //return this.stateRepository.findAll();
    }

    public void updateState(int id, State state) {
        if(this.stateRepository.existsById(id)){
            state.setId(id);
            this.stateRepository.save(state);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteState(int id) {
        if(this.stateRepository.existsById(id)){
            this.stateRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteAllStates() {
        this.stateRepository.deleteAll();
    }
}
