package com.example.demo.database.entity;

public class UserFiles {
    private int id;
    private int userId;
    private String file_type;

    private String file_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public UserFiles() {
    }

    public UserFiles(int id, int userId, String file_type, String file_path) {
        this.id = id;
        this.userId = userId;
        this.file_type = file_type;
        this.file_path = file_path;
    }
}
