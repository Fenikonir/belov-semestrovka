package com.example.demo.database.entity;

import java.sql.Timestamp;

public class Train {
    private int id;
    private String trainNumber;
    private City city;
    private Timestamp lastModified;

    public Train(int id, String trainNumber, City city, Timestamp lastModified) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.city = city;
        this.lastModified = lastModified;
    }

    public Train() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    // Getters and Setters
}