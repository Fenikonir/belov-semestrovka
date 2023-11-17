package com.belov.semestrovka.database.dao.impl;

import com.belov.semestrovka.database.entity.TransportQueries;

import java.sql.Connection;

public class BusDAOImpl extends UniversalTransportDaoImpl {
    private static final String SELECT_TRANSPORT_BY_ID = "SELECT * FROM buses WHERE id = ?";
    private static final String SELECT_TRANSPORT_BY_PARAMETER = "SELECT * FROM buses WHERE %s = ?";
    private static final String SELECT_ALL_TRANSPORTS = "SELECT * FROM buses";
    private static final String INSERT_TRANSPORT = "INSERT INTO buses (bus_number, city_id, fare) VALUES (?, ?, ?)";
    private static final String UPDATE_TRANSPORT = "UPDATE buses SET bus_number = ?, city_id = ?, fare = ?, last_modified = ? WHERE id = ?";
    private static final String DELETE_TRANSPORT = "DELETE FROM buses WHERE id = ?";
    private static final String SELECT_TRANSPORTS_BY_CITY = "SELECT * FROM buses WHERE city_id = ?";
    public BusDAOImpl(Connection connection) {
        super(connection);
    }

    public String getSQLScript(TransportQueries t) {
        switch (t) {
            case SELECT_TRANSPORT_BY_ID:
                return SELECT_TRANSPORT_BY_ID;
            case SELECT_TRANSPORT_BY_PARAMETER:
                return SELECT_TRANSPORT_BY_PARAMETER;
            case SELECT_ALL_TRANSPORTS:
                return SELECT_ALL_TRANSPORTS;
            case INSERT_TRANSPORT:
                return INSERT_TRANSPORT;
            case UPDATE_TRANSPORT:
                return UPDATE_TRANSPORT;
            case DELETE_TRANSPORT:
                return DELETE_TRANSPORT;
            case SELECT_TRANSPORTS_BY_CITY:
                return SELECT_TRANSPORTS_BY_CITY;
            case TR_TYPE: return "bus";
            default:
                return "";
        }
    }


}