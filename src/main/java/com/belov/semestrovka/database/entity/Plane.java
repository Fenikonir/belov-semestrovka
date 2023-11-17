package com.belov.semestrovka.database.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Plane extends Transport {
    public Plane(int id, String planeNumber, City city, LocalDateTime lastModified, List<TransportStop> transportStopList) {
        super(id, planeNumber, city, lastModified, transportStopList);
    }
    @Override
    public String getMatName() {
        return "Самолеты";
    }

    public Plane() {
    }
}