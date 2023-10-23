package com.example.demo.database.entity;

import java.time.LocalDateTime;

public class Bus {
    private int id;
    private String busNumber;
    private City city;
    private LocalDateTime lastModified;

    public Bus(int id, String busNumber, City city, LocalDateTime lastModified) {
        this.id = id;
        this.busNumber = busNumber;
        this.city = city;
        this.lastModified = lastModified;
    }

    public Bus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
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