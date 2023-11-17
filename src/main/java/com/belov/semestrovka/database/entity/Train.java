package com.belov.semestrovka.database.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Train extends Transport {
    public Train(int id, String trainNumber, City city, LocalDateTime lastModified, List<TransportStop> transportStopList) {
        super(id, trainNumber, city, lastModified, transportStopList);
    }
    @Override
    public String getMatName() {
        return "Поезда";
    }
    public Train() {
    }
}