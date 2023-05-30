package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.configuration.DataSourceConfiguration;
import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.utils.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class OpenWeatherService {
    private final static String API_KEY = "{API key}";
    private final static String LATITUDE = "{lat}";
    private final static String LONGITUDE = "{lon}";
    private final static String CITY_NAME = "{city name}";

    private final int CONN_TIMEOUT = 5000;
    private final int READ_TIMEOUT = 5000;

    DataSourceConfiguration dataSourceConfiguration;
    private ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler;
    private CityService cityService;
    private MeasurementService measurementService;

    private static final Logger log = LoggerFactory.getLogger(OpenWeatherService.class);

    @Autowired
    public OpenWeatherService(DataSourceConfiguration dataSourceConfiguration, ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler, CityService cityService, MeasurementService measurementService) {
        this.dataSourceConfiguration = dataSourceConfiguration;
        this.weatherThreadPoolTaskScheduler = weatherThreadPoolTaskScheduler;
        this.cityService = cityService;
        this.measurementService = measurementService;
        this.startThread();
    }

    private void startThread() {
        weatherThreadPoolTaskScheduler.scheduleAtFixedRate(this::requestToOpenWeather, dataSourceConfiguration.getUpdateRate()*1000);
    }

    private void requestToOpenWeather() {
        List<City> cities = this.cityService.getAllCities();
        if(cities.size() == 0) {
            return;
        }

        for (City city:cities) {
            requestToApi(city);
        }
    }

    private void requestToApi(City city) {
        String urlGeocoding = getGeocodingUrl(city.getName());

        String responseCity = getResponse(urlGeocoding);

        Pair<String, String> latitudeLongitude = JSONParser.getLatitudeLongitude(responseCity);
        String latitude = latitudeLongitude.getFirst();
        String longitude = latitudeLongitude.getSecond();
        String url = getUrl(latitude, longitude);

        String responseWeather = getResponse(url);
        Measurement measurement = JSONParser.getMeasurementFromResponse(responseWeather);
        measurement.setCity(city.getName());

        //measurementService.addNewMeasurement(measurement);
    }

    private String getGeocodingUrl(String cityName) {
        String urlGeocoding = this.dataSourceConfiguration.getUrlGeocoding();
        cityName = editCityName(cityName);
        urlGeocoding = urlGeocoding.replace(CITY_NAME, cityName).replace(API_KEY, this.dataSourceConfiguration.getApiKey());
        return urlGeocoding;
    }

    private String editCityName(String cityName) {
        cityName = cityName.trim();
        cityName = cityName.replace(" ", "_");
        return cityName;
    }

    private String getResponse(String urlGeocodingString) {
        try {
            URL urlGeocoding = new URL(urlGeocodingString);
            HttpURLConnection connection = (HttpURLConnection) urlGeocoding.openConnection();
            connection.setConnectTimeout(CONN_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            return builder.toString();

        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getUrl(String latitude, String longitude) {
        String url = this.dataSourceConfiguration.getUrl();
        url = url.replace(LATITUDE, latitude).replace(LONGITUDE, longitude).replace(API_KEY, this.dataSourceConfiguration.getApiKey());
        return url;
    }

}
