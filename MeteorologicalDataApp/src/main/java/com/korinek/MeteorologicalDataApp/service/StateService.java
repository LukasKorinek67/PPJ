package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.State;
import com.korinek.MeteorologicalDataApp.repository.StateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    public void addNewState(State state) {
        if(this.stateRepository.existsById(state.getId())){
            throw new DuplicateKeyException("Already exists!");
        } else {
            this.stateRepository.save(state);
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
        return this.stateRepository.findAll();
    }

    public void updateState(int id, State state) {
        if(this.stateRepository.existsById(id)){
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
