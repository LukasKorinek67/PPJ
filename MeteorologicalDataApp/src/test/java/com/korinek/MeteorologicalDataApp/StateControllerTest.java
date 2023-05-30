package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.controller.CityController;
import com.korinek.MeteorologicalDataApp.controller.StateController;
import com.korinek.MeteorologicalDataApp.model.State;
import com.korinek.MeteorologicalDataApp.service.StateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(StateController.class)
public class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StateService stateService;

    @InjectMocks
    private StateController stateController;

    @Test
    public void testAddNewState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/state")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"California\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testAddNewStates() throws Exception {
        List<State> states = new ArrayList<>();
        states.add(new State("California"));
        states.add(new State("Texas"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/states")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"California\"},{\"name\":\"Texas\"}]"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetState() throws Exception {
        State state = new State("California");
        when(stateService.getState(1)).thenReturn(state);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/state/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("California"));
    }

    @Test
    public void testGetAllStates() throws Exception {
        List<State> states = new ArrayList<>();
        states.add(new State("California"));
        states.add(new State("Texas"));
        when(stateService.getAllStates()).thenReturn(states);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/states"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("California"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Texas"));
    }

    @Test
    public void testUpdateState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/state/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"California\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/state/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAllStates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/states"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

