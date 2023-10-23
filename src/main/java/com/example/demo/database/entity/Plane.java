package com.example.demo.database.entity;

import java.time.LocalDateTime;

public class Plane {
    private int id;
    private String planeNumber;
    private City city;
    private LocalDateTime lastModified;

    public Plane(int id, String planeNumber, City city, LocalDateTime lastModified) {
        this.id = id;
        this.planeNumber = planeNumber;
        this.city = city;
        this.lastModified = lastModified;
    }

    public Plane() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
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