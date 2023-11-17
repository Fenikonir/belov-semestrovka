package com.belov.semestrovka.database.dao.impl;

import com.belov.semestrovka.database.entity.Plane;
import com.belov.semestrovka.database.entity.TransportQueries;

import java.sql.Connection;

public class PlaneDAOImpl extends UniversalTransportDaoImpl {
    private static final String SELECT_TRANSPORT_BY_ID = "SELECT * FROM planes WHERE id = ?";
    private static final String SELECT_TRANSPORT_BY_PARAMETER = "SELECT * FROM planes WHERE %s = ?";
    private static final String SELECT_ALL_TRANSPORTS = "SELECT * FROM planes";
    private static final String INSERT_TRANSPORT = "INSERT INTO planes (plane_number, city_id, fare) VALUES (?, ?, ?)";
    private static final String UPDATE_TRANSPORT = "UPDATE planes SET plane_number = ?, city_id = ?, fare = ?, last_modified = ? WHERE id = ?";
    private static final String DELETE_TRANSPORT = "DELETE FROM planes WHERE id = ?";
    private static final String SELECT_TRANSPORTS_BY_CITY = "SELECT * FROM planes WHERE city_id = ?";

    public PlaneDAOImpl(Connection connection) {
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
            case TR_TYPE: return "plane";
            default:
                return "";
        }
    }


}