package com.belov.semestrovka.database.dao;

import java.util.List;

public interface
DAO<T> {
    T getById(int id);
    T getByParameter(String p, String v);
    List<T> getAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);


}