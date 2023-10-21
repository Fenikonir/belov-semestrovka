package com.example.demo.database.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String password;
    private Timestamp creationDate;
    private Timestamp lastModified;

    public User(int id, String username, String password, Timestamp creationDate, Timestamp lastModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    // Getters and Setters
}