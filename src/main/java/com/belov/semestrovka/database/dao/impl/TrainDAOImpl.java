package com.belov.semestrovka.database.dao.impl;

import com.belov.semestrovka.database.entity.TransportQueries;

import java.sql.Connection;

public class TrainDAOImpl extends UniversalTransportDaoImpl {
    private static final String SELECT_TRANSPORT_BY_ID = "SELECT * FROM trains WHERE id = ?";
    private static final String SELECT_TRANSPORT_BY_PARAMETER = "SELECT * FROM trains WHERE %s = ?";
    private static final String SELECT_ALL_TRANSPORTS = "SELECT * FROM trains";
    private static final String INSERT_TRANSPORT = "INSERT INTO trains (train_number, city_id, fare) VALUES (?, ?, ?)";
    private static final String UPDATE_TRANSPORT = "UPDATE trains SET train_number = ?, city_id = ?, fare = ?, last_modified = ? WHERE id = ?";
    private static final String DELETE_TRANSPORT = "DELETE FROM trains WHERE id = ?";
    private static final String SELECT_TRANSPORTS_BY_CITY = "SELECT * FROM trains WHERE city_id = ?";

    public TrainDAOImpl(Connection connection) {
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
            case TR_TYPE: return "train";
            default:
                return "";
        }
    }


}