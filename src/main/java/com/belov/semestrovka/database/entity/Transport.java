package com.belov.semestrovka.database.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Transport {
    public enum TransportType {
        REGULAR,
        ORDERED,
        PERSONAL
    }

    private int id;
    private String transportNumber;
    private City city;
    private LocalDateTime lastModified;
    private double fare;
    private TransportType transportType;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    private LocalDate createdAt;


    public List<TransportStop> getTransportStopList() {
        return transportStopList;
    }

    public void setTransportStopList(List<TransportStop> transportStopList) {
        this.transportStopList = transportStopList;
    }

    private List<TransportStop> transportStopList;

    public Transport(int id, String transportNumber, City city, LocalDateTime lastModified, List<TransportStop> transportStopList) {
        this.id = id;
        this.transportNumber = transportNumber;
        this.city = city;
        this.lastModified = lastModified;
        this.transportStopList = transportStopList;
    }

    public Transport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
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

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getMatName() {
        return null;
    }

    public TransportType getTransportType() {
        return transportType;
    }
    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
}