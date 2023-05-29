package com.korinek.MeteorologicalDataApp.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.model.WeatherAverages;
import com.korinek.MeteorologicalDataApp.utils.Timestamp;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.PreparedStatementBinder;
import org.springframework.data.cassandra.core.cql.PreparedStatementCreator;
import org.springframework.data.cassandra.core.cql.QueryOptions;
import org.springframework.data.cassandra.core.query.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@Repository
public class MeasurementRepository {

    private CassandraTemplate cassandraTemplate;

    @Autowired
    public MeasurementRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public boolean existsById(UUID id) {
        return this.cassandraTemplate.exists(id, Measurement.class);
    }

    public void save(Measurement measurement) {
        boolean successful = false;
        while (!successful) {
            UUID newId = UUID.randomUUID();
            measurement.setId(newId);
            //this.cassandraTemplate.insert(measurement);
            Measurement insertedMeasurement = this.cassandraTemplate.insert(measurement);
            if(insertedMeasurement != null) {
                successful = true;
            }
        }
    }

    public Measurement findById(UUID id) {
        return this.cassandraTemplate.selectOneById(id, Measurement.class);
    }

    public Iterable<Measurement> findAll() {
        List<Measurement> allMeasurements = cassandraTemplate.select("SELECT * FROM Measurements", Measurement.class);
        return allMeasurements;
    }

    public void deleteById(UUID id) {
        this.cassandraTemplate.deleteById(id, Measurement.class);
    }

    public void deleteAll() {
        this.cassandraTemplate.truncate(Measurement.class);
    }

    public Measurement getLatestMeasurement(String city_name) {
        Query query = query(where("city").is(city_name)).limit(1);
        return cassandraTemplate.selectOne(query, Measurement.class);
    }

    public WeatherAverages getAverageWeatherFrom(String cityName, long timestampFrom) {

        cassandraTemplate.setUsePreparedStatements(true);

        ResultSet resultSet = cassandraTemplate.execute(SimpleStatement.newInstance("SELECT AVG(temperature) AS avgTemperature, " +
                "AVG(feels_like_temperature) AS avgFeelsLikeTemperature, " +
                "AVG(pressure) AS avgPressure, " +
                "AVG(humidity) AS avgHumidity, " +
                "AVG(visibility) AS avgVisibility, " +
                "AVG(wind_speed) AS avgWindSpeed, " +
                "AVG(cloudiness) AS avgCloudiness " +
                "FROM measurements WHERE city = ? AND timestamp >= ?", cityName, timestampFrom));

        Row row = resultSet.one();
        double avgTemperature = row.getDouble("avgTemperature");
        double avgFeelsLikeTemperature = row.getDouble("avgFeelsLikeTemperature");
        int avgPressure = row.getInt("avgPressure");
        int avgHumidity = row.getInt("avgHumidity");
        int avgVisibility = row.getInt("avgVisibility");
        double avgWindSpeed = row.getDouble("avgWindSpeed");
        int avgCloudiness = row.getInt("avgCloudiness");
        WeatherAverages averages = new WeatherAverages(cityName, avgTemperature, avgFeelsLikeTemperature, avgPressure, avgHumidity, avgVisibility, avgWindSpeed, avgCloudiness);
        return averages;
    }
}
