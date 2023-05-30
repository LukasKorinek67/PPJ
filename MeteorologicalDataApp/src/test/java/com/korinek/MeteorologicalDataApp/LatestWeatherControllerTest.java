package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.controller.LatestWeatherController;
import com.korinek.MeteorologicalDataApp.controller.MeasurementController;
import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.service.CityService;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(LatestWeatherController.class)
public class LatestWeatherControllerTest {

    @Mock
    private CityService cityService;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private LatestWeatherController latestWeatherController;

    private MockMvc mockMvc;

    @Test
    public void testGetLatestWeather() throws Exception {
        int cityId = 123;
        City city = new City();
        city.setId(cityId);
        when(cityService.getCity(cityId)).thenReturn(city);

        Measurement measurement = new Measurement();
        measurement.setCity("City Name");
        measurement.setTemperature(25.5);
        measurement.setFeelsLikeTemperature(24.0);
        measurement.setPressure(1015);
        measurement.setHumidity(60);
        measurement.setVisibility(10000);
        measurement.setWindSpeed(6.8);
        measurement.setCloudiness(30);
        when(measurementService.getLatestMeasurement(city)).thenReturn(measurement);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/latest/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("City Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.feelsLikeTemperature").value(24.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(60))
                .andExpect(MockMvcResultMatchers.jsonPath("$.visibility").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(6.8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloudiness").value(30));
    }
}
