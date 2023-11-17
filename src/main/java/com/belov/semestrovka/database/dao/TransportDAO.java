package com.belov.semestrovka.database.dao;

import com.belov.semestrovka.database.entity.Transport;

import java.util.List;

public interface TransportDAO<T> {
    List<T> getByCity(int cityId);
}
