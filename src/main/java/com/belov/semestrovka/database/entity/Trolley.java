package com.belov.semestrovka.database.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Trolley extends Transport {
    public Trolley(int id, String trolleyNumber, City city, LocalDateTime lastModified, List<TransportStop> transportStopList) {
        super(id, trolleyNumber, city, lastModified, transportStopList);
    }
    @Override
    public String getMatName() {
        return "Троллейбусы";
    }
    public Trolley() {
    }
}