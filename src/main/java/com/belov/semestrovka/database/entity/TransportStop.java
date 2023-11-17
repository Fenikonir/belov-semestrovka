package com.belov.semestrovka.database.entity;

import java.util.Random;

public class TransportStop {
    private int id;
    private String name;
    private String address;
    private String openingTime;
    private int capacity;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public boolean isHasRoof() {
        return hasRoof;
    }

    public boolean isHasPhoneCharging() {
        return hasPhoneCharging;
    }

    private int cityId;
    private boolean hasRoof;
    private boolean hasPhoneCharging;

    public TransportStop(int id, String name, String address, String openingTime, int capacity, int cityId, boolean hasRoof, boolean hasPhoneCharging) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.openingTime = openingTime;
        this.capacity = capacity;
        this.cityId = cityId;
        this.hasRoof = hasRoof;
        this.hasPhoneCharging = hasPhoneCharging;
    }

    public static String getRandomColor() {
        String[] colors = {
                "bg-primary",
                "bg-secondary",
                "bg-success",
                "bg-danger",
                "bg-warning",
                "bg-info",
                "bg-dark"
        };

        Random random = new Random();
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean hasRoof() {
        return hasRoof;
    }

    public void setHasRoof(boolean hasRoof) {
        this.hasRoof = hasRoof;
    }

    public boolean hasPhoneCharging() {
        return hasPhoneCharging;
    }

    public void setHasPhoneCharging(boolean hasPhoneCharging) {
        this.hasPhoneCharging = hasPhoneCharging;
    }
}
