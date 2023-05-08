package com.korinek.MeteorologicalDataApp.repository;

import com.korinek.MeteorologicalDataApp.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementRepository {

    private CassandraTemplate cassandraTemplate;

    @Autowired
    public MeasurementRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public boolean existsById(int id) {
        return this.cassandraTemplate.exists(id, Measurement.class);
    }

    public void save(Measurement measurement) {
        this.cassandraTemplate.insert(measurement);
    }

    public Measurement findById(int id) {
        return this.cassandraTemplate.selectOneById(id, Measurement.class);
    }

    public Iterable<Measurement> findAll() {
        List<Measurement> allMeasurements = cassandraTemplate.select("SELECT * FROM Measurement", Measurement.class);
        return allMeasurements;
    }

    public void deleteById(int id) {
        this.cassandraTemplate.deleteById(id, Measurement.class);
    }

    public void deleteAll() {
        this.cassandraTemplate.truncate(Measurement.class);
    }
}
