package com.korinek.MeteorologicalDataApp.model;


import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Measurement {

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
}
