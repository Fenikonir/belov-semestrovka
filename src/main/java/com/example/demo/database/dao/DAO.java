package com.example.demo.database.dao;

import java.util.List;

public interface DAO<T> {
    T getById(int id);
    List<T> getAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}