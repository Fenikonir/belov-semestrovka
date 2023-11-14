package com.example.demo.database.entity;

import com.example.demo.database.repository.PgRepository;

import java.time.LocalDateTime;

public class Article {
    private int id;
    private int authorId;
    private String type;

    public String getPhoto() {
        if (photo == null) {
            return "https://bootdey.com/img/Content/avatar/avatar3.png";
        }
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String photo;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private LocalDateTime createdDate;

    private String value;

    public Article(int id, int authorId, String type, String value) {
        this.id = id;
        this.authorId = authorId;
        this.type = type;
        this.value = value;
    }

    public Article() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
