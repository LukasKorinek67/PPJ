package com.korinek.MeteorologicalDataApp.repository;

import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.CriteriaDefinition;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
        Query query = Query.query(Criteria.where("city").is(city_name)).limit(1);
        return cassandraTemplate.selectOne(query, Measurement.class);
    }
}
