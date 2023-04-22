package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.State;
import com.korinek.MeteorologicalDataApp.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/state")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @PostMapping
    public ResponseEntity<State> addNewState(@RequestBody NewStateRequest newState){
        try {
            State state = new State(newState.name());
            this.stateService.addNewState(state);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    record NewStateRequest(String name){}

    @GetMapping(path="{stateId}")
    public ResponseEntity<State> getState(@PathVariable("stateId") int id){
        try{
            State state = this.stateService.getState(id);
            return new ResponseEntity<>(state, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<State>> getAllStates(){
        return new ResponseEntity<>(this.stateService.getAllStates(), HttpStatus.OK);
    }

    @PatchMapping(path="{stateId}")
    public ResponseEntity<State> updateState(@PathVariable("stateId") int id, @RequestBody NewStateRequest newState){
        try{
            State state = new State(newState.name());
            state.setId(id);
            this.stateService.updateState(id, state);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="{stateId}")
    public ResponseEntity<State> deleteState(@PathVariable("stateId") int id){
        try{
            this.stateService.deleteState(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<State> deleteAllStates(){
        this.stateService.deleteAllStates();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
