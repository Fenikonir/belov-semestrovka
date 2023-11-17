package com.belov.semestrovka.database.entity;

public enum TransportQueries {
    SELECT_TRANSPORT_BY_ID("SELECT * FROM %s WHERE id = ?"),
    SELECT_TRANSPORT_BY_PARAMETER("SELECT * FROM %s WHERE %s = ?"),
    SELECT_ALL_TRANSPORTS("SELECT * FROM %s"),
    INSERT_TRANSPORT("INSERT INTO %s (%s_number, city_id, fare) VALUES (?, ?, ?)"),
    UPDATE_TRANSPORT("UPDATE %s SET %s_number = ?, city_id = ?, fare = ?, last_modified = ? WHERE id = ?"),
    DELETE_TRANSPORT("DELETE FROM %s WHERE id = ?"),
    SELECT_TRANSPORTS_BY_CITY("SELECT * FROM %s WHERE city_id = ?"),
    TR_TYPE("transport");

    private final String query;

    TransportQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}