package com.example.demo.database.dao;

import java.util.List;

public interface TransportDAO<T> {
    List<T> getByCity(int cityId);
}
