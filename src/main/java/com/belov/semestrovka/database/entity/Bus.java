package com.belov.semestrovka.database.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Bus extends Transport {
    public Bus(int id, String busNumber, City city, LocalDateTime lastModified, List<TransportStop> transportStopList) {
        super(id, busNumber, city, lastModified, transportStopList);
    }

    @Override
    public String getMatName() {
        return "Автобусы";
    }

    public Bus() {
    }
}