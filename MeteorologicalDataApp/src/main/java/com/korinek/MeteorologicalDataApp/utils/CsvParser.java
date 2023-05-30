package com.korinek.MeteorologicalDataApp.utils;

import com.korinek.MeteorologicalDataApp.model.Measurement;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static List<Measurement> parseCSV(String csv, String cityName) {
        List<Measurement> measurements = new ArrayList<>();

        String[] allLines = csv.split("\n");

        // vyhodím první řádek
        String[] lines = new String[allLines.length - 1];
        System.arraycopy(allLines, 1, lines, 0, allLines.length - 1);

        String[] values;

        long timestamp;
        String weather;
        String weatherDescription;
        double temperature;
        double feelsLikeTemperature;
        int pressure;
        int humidity;
        int visibility;
        double windSpeed;
        int cloudiness;

        int timestampIndex = 0;
        int weatherIndex = 1;
        int weatherDescriptionIndex = 2;
        int temperatureIndex = 3;
        int feelsLikeTemperatureIndex = 4;
        int pressureIndex = 5;
        int humidityIndex = 6;
        int visibilityIndex = 7;
        int windSpeedIndex = 8;
        int cloudinessIndex = 9;

        for (String line : lines) {
            //
            System.out.println(line);
            //
            values = line.split(";");
            timestamp = Long.parseLong(values[timestampIndex]);
            weather = values[weatherIndex];
            weatherDescription = values[weatherDescriptionIndex];
            temperature = Double.parseDouble(values[temperatureIndex]);
            feelsLikeTemperature = Double.parseDouble(values[feelsLikeTemperatureIndex]);
            pressure = Integer.parseInt(values[pressureIndex]);
            humidity = Integer.parseInt(values[humidityIndex]);
            visibility = Integer.parseInt(values[visibilityIndex]);
            windSpeed = Double.parseDouble(values[windSpeedIndex]);
            //
            System.out.println(windSpeed);
            //
            cloudiness = Integer.parseInt(values[cloudinessIndex].trim());
            //
            System.out.println(cloudiness);
            //

            Measurement measurement = new Measurement();
            measurement.setTimestamp(timestamp);
            measurement.setWeather(weather);
            measurement.setWeatherDescription(weatherDescription);
            measurement.setTemperature(temperature);
            measurement.setFeelsLikeTemperature(feelsLikeTemperature);
            measurement.setPressure(pressure);
            measurement.setHumidity(humidity);
            measurement.setVisibility(visibility);
            measurement.setWindSpeed(windSpeed);
            measurement.setCloudiness(cloudiness);
            measurement.setCity(cityName);
            measurements.add(measurement);
        }
        return  measurements;
    }
}
