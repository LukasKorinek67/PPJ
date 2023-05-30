package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.controller.AverageWeatherController;
import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.WeatherAverages;
import com.korinek.MeteorologicalDataApp.service.CityService;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(AverageWeatherController.class)
public class AverageWeatherControllerTest {

    @Mock
    private CityService cityService;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private AverageWeatherController averageWeatherController;

    private MockMvc mockMvc;

    @Test
    public void testGetAverageWeatherLastDay() throws Exception {
        int cityId = 123;
        City city = new City();
        city.setId(cityId);
        when(cityService.getCity(cityId)).thenReturn(city);

        WeatherAverages weatherAverages = new WeatherAverages("City Name", 25.5, 24.8, 1012, 70, 10000, 5.5, 30);
        when(measurementService.getAverageWeatherLastDay(city)).thenReturn(weatherAverages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/average/day/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("City Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.feelsLikeTemperature").value(24.8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure").value(1012))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(70))
                .andExpect(MockMvcResultMatchers.jsonPath("$.visibility").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(5.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloudiness").value(30));
    }

    @Test
    public void testGetAverageWeatherLastWeek() throws Exception {
        int cityId = 123;
        City city = new City();
        city.setId(cityId);
        when(cityService.getCity(cityId)).thenReturn(city);

        WeatherAverages weatherAverages = new WeatherAverages("City Name", 22.0, 20.0, 1015, 70, 8000, 6.2, 40);
        when(measurementService.getAverageWeatherLastWeek(city)).thenReturn(weatherAverages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/average/week/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("City Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(22.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.feelsLikeTemperature").value(20.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(70))
                .andExpect(MockMvcResultMatchers.jsonPath("$.visibility").value(8000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(6.2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloudiness").value(40));
    }

    @Test
    public void testGetAverageWeatherLast14Days() throws Exception {
        int cityId = 123;
        City city = new City();
        city.setId(cityId);
        when(cityService.getCity(cityId)).thenReturn(city);

        WeatherAverages weatherAverages = new WeatherAverages("City Name", 23.5, 21.5, 1012, 68, 9000, 5.8, 45);
        when(measurementService.getAverageWeatherLast14Days(city)).thenReturn(weatherAverages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/average/14days/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("City Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(23.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.feelsLikeTemperature").value(21.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure").value(1012))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(68))
                .andExpect(MockMvcResultMatchers.jsonPath("$.visibility").value(9000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(5.8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloudiness").value(45));
    }
}

