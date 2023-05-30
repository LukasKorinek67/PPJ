package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.controller.MeasurementController;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
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
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(MeasurementController.class)
public class MeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    @Test
    public void testAddNewMeasurement() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/measurement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"timestamp\": 1622376000, \"id\": \"d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd\", \"weather\": \"Sunny\", \"weatherDescription\": \"Clear sky\", \"temperature\": 25.5, \"feelsLikeTemperature\": 26.8, \"pressure\": 1012, \"humidity\": 65, \"visibility\": 10000, \"windSpeed\": 3.5, \"cloudiness\": 0, \"city\": \"Prague\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testAddNewMeasurements() throws Exception {
        List<Measurement> measurements = new ArrayList<>();
        measurements.add(createMeasurement(1622376000, "d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd", "Sunny", "Clear sky", 25.5, 26.8, 1012, 65, 10000, 3.5, 0, "Prague"));
        measurements.add(createMeasurement(1622377000, "e71a9a9f-27d3-4e55-a0b0-3c57112fb53d", "Rainy", "Heavy rain", 18.3, 19.6, 1008, 75, 5000, 2.8, 90, "Berlin"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"timestamp\": 1622376000, \"id\": \"d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd\", \"weather\": \"Sunny\", \"weatherDescription\": \"Clear sky\", \"temperature\": 25.5, \"feelsLikeTemperature\": 26.8, \"pressure\": 1012, \"humidity\": 65, \"visibility\": 10000, \"windSpeed\": 3.5, \"cloudiness\": 0, \"city\": \"Prague\"},{\"timestamp\": 1622377000, \"id\": \"e71a9a9f-27d3-4e55-a0b0-3c57112fb53d\", \"weather\": \"Rainy\", \"weatherDescription\": \"Heavy rain\", \"temperature\": 18.3, \"feelsLikeTemperature\": 19.6, \"pressure\": 1008, \"humidity\": 75, \"visibility\": 5000, \"windSpeed\": 2.8, \"cloudiness\": 90, \"city\": \"Berlin\"}]"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetMeasurement() throws Exception {
        UUID id = UUID.fromString("d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd");
        Measurement measurement = createMeasurement(1622376000, id.toString(), "Sunny", "Clear sky", 25.5, 26.8, 1012, 65, 10000, 3.5, 0, "Prague");

        when(measurementService.getMeasurement(id)).thenReturn(measurement);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurements/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather").value("Sunny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Prague"));
    }

    @Test
    public void testGetAllMeasurements() throws Exception {
        List<Measurement> measurements = new ArrayList<>();
        measurements.add(createMeasurement(1622376000, "d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd", "Sunny", "Clear sky", 25.5, 26.8, 1012, 65, 10000, 3.5, 0, "Prague"));
        measurements.add(createMeasurement(1622377000, "e71a9a9f-27d3-4e55-a0b0-3c57112fb53d", "Rainy", "Heavy rain", 18.3, 19.6, 1008, 75, 5000, 2.8, 90, "Berlin"));

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurements"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("e71a9a9f-27d3-4e55-a0b0-3c57112fb53d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather").value("Sunny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].weather").value("Rainy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Prague"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].city").value("Berlin"));
    }

    @Test
    public void testUpdateMeasurement() throws Exception {
        UUID id = UUID.fromString("d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd");
        Measurement newMeasurement = createMeasurement(1622376000, id.toString(), "Sunny", "Clear sky", 25.5, 26.8, 1012, 65, 10000, 3.5, 0, "Prague");

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/measurements/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"timestamp\": 1622376000, \"id\": \"d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd\", \"weather\": \"Rainy\", \"weatherDescription\": \"Heavy rain\", \"temperature\": 20.3, \"feelsLikeTemperature\": 22.6, \"pressure\": 1008, \"humidity\": 75, \"visibility\": 5000, \"windSpeed\": 2.8, \"cloudiness\": 90, \"city\": \"Berlin\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteMeasurement() throws Exception {
        UUID id = UUID.fromString("d0b33c3c-7828-4eb5-98b4-62c0a9f8b0fd");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/measurements/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAllMeasurements() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/measurements"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private Measurement createMeasurement(long timestamp, String id, String weather, String weatherDescription, double temperature, double feelsLikeTemperature, int pressure, int humidity, int visibility, double windSpeed, int cloudiness, String city) {
        Measurement measurement = new Measurement();
        measurement.setTimestamp(timestamp);
        measurement.setId(UUID.fromString(id));
        measurement.setWeather(weather);
        measurement.setWeatherDescription(weatherDescription);
        measurement.setTemperature(temperature);
        measurement.setFeelsLikeTemperature(feelsLikeTemperature);
        measurement.setPressure(pressure);
        measurement.setHumidity(humidity);
        measurement.setVisibility(visibility);
        measurement.setWindSpeed(windSpeed);
        measurement.setCloudiness(cloudiness);
        measurement.setCity(city);
        return measurement;
    }
}

