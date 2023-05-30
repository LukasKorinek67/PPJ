package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.controller.CityController;
import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @Test
    public void testAddNewCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Prague\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testAddNewCities() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Prague"));
        cities.add(new City("Berlin"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Prague\"},{\"name\":\"Berlin\"}]"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetCity() throws Exception {
        City city = new City("Prague");
        when(cityService.getCity(1)).thenReturn(city);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/city/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Prague"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Prague"));
        cities.add(new City("Berlin"));
        when(cityService.getAllCities()).thenReturn(cities);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Prague"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Berlin"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/city/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Prague\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/city/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAllCities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cities"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
