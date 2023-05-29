package com.korinek.MeteorologicalDataApp.model;


public record WeatherAverages (String city, double temperature, double feelsLikeTemperature, int pressure, int humidity, int visibility, double windSpeed, int cloudiness){
}
