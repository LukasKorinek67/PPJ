package com.korinek.MeteorologicalDataApp.model;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("measurements")
public class Measurement {

    //@Column("timestamp")
    @PrimaryKeyColumn(name = "timestamp", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING, ordinal = 1)
    private long timestamp;
    //@PrimaryKey
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    private UUID id;
    @Column("weather")
    private String weather;
    @Column("weather_description")
    private String weatherDescription;
    @Column("temperature")
    private double temperature;
    @Column("feels_like_temperature")
    private double feelsLikeTemperature;
    @Column("pressure")
    private int pressure;
    @Column("humidity")
    private int humidity;
    @Column("visibility")
    private int visibility;
    @Column("wind_speed")
    private double windSpeed;
    @Column("cloudiness")
    private int cloudiness;

    @PrimaryKeyColumn(name = "city", type = PrimaryKeyType.PARTITIONED)
    private String city;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "weather='" + weather + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", windSpeed=" + windSpeed +
                ", cloudiness=" + cloudiness +
                ", timestamp=" + timestamp +
                ", city=" + city +
                '}';
    }

    // ---------------------------
/*
    @PrimaryKey
    private int id;

    @Column("city")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String City;

    public Measurement() {}

    public Measurement(int id) {
        this.id = id;
    }


    public Measurement(int id, String city) {
        this.id = id;
        City = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
    */
}
