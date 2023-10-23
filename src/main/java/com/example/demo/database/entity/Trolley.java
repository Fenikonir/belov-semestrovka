package com.example.demo.database.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Trolley {
    private int id;
    private String trolleyNumber;
    private City city;
    private LocalDateTime lastModified;

    public Trolley(int id, String trolleyNumber, City city, LocalDateTime lastModified) {
        this.id = id;
        this.trolleyNumber = trolleyNumber;
        this.city = city;
        this.lastModified = lastModified;
    }

    public Trolley() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrolleyNumber() {
        return trolleyNumber;
    }

    public void setTrolleyNumber(String trolleyNumber) {
        this.trolleyNumber = trolleyNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    // Getters and Setters
}