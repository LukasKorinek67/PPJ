package com.korinek.MeteorologicalDataApp.utils;

import com.korinek.MeteorologicalDataApp.model.Measurement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.util.Pair;

public class JSONParser {

    public static Pair<String, String> getLatitudeLongitude(String responseCity) {
        try {
            JSONArray jsonArray = new JSONArray(responseCity);
            JSONObject city = jsonArray.getJSONObject(0);
            String name = city.getString("name");
            double latitude = city.getDouble("lat");
            double longitude = city.getDouble("lon");
            Pair<String, String> latitudeLongitude = Pair.of(String.valueOf(latitude), String.valueOf(longitude));
            return latitudeLongitude;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Measurement getMeasurementFromResponse(String responseWeather) {
        try {
            JSONObject data = new JSONObject(responseWeather);
            Measurement measurement = new Measurement();
            JSONObject weatherObject = data.getJSONArray("weather").getJSONObject(0);
            String weather = weatherObject.getString("main");
            measurement.setWeather(weather);
            String weatherDescription = weatherObject.getString("description");
            measurement.setWeatherDescription(weatherDescription);
            JSONObject mainObject = data.getJSONObject("main");
            double temperature = mainObject.getDouble("temp");
            measurement.setTemperature(temperature);
            double feelsLikeTemperature = mainObject.getDouble("feels_like");
            measurement.setFeelsLikeTemperature(feelsLikeTemperature);
            int pressure = mainObject.getInt("pressure");
            measurement.setPressure(pressure);
            int humidity = mainObject.getInt("humidity");
            measurement.setHumidity(humidity);

            int visibility = data.getInt("visibility");
            measurement.setVisibility(visibility);

            double windSpeed = data.getJSONObject("wind").getDouble("speed");
            measurement.setWindSpeed(windSpeed);
            int cloudiness = data.getJSONObject("clouds").getInt("all");
            measurement.setCloudiness(cloudiness);
            long timestamp = data.getLong("dt");
            measurement.setTimestamp(timestamp);
            return measurement;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
