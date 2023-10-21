package com.example.demo.database.entity;

import java.sql.Timestamp;

public class City {
    private int id;
    private String cityName;
    private int busCount;
    private int planeCount;
    private int trolleyCount;
    private int trainCount;
    private Timestamp lastModified;

    public City(int id, String cityName, int busCount, int planeCount, int trolleyCount, int trainCount, Timestamp lastModified) {
        this.id = id;
        this.cityName = cityName;
        this.busCount = busCount;
        this.planeCount = planeCount;
        this.trolleyCount = trolleyCount;
        this.trainCount = trainCount;
        this.lastModified = lastModified;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getBusCount() {
        return busCount;
    }

    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    public int getPlaneCount() {
        return planeCount;
    }

    public void setPlaneCount(int planeCount) {
        this.planeCount = planeCount;
    }

    public int getTrolleyCount() {
        return trolleyCount;
    }

    public void setTrolleyCount(int trolleyCount) {
        this.trolleyCount = trolleyCount;
    }

    public int getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(int trainCount) {
        this.trainCount = trainCount;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    // Getters and Setters
}
