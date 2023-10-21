package com.example.demo.database.dao;

import com.example.demo.database.dao.impl.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFabric {

    public static final String url = "jdbc:postgresql://localhost:8848/semestrovka";
    public static final String username = "postgres";
    public static final String password = "postgres";
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    public static DAO userDAO = null;
    public static DAO planeDAO = null;
    public static DAO busDAO = null;
    public static DAO cityDAO = null;
    public static DAO trainDAO = null;
    public static DAO trolleyDAO = null;

    public static DAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl(connection);
        }
        return userDAO;
    }

    public static DAO getPlaneDAO() {
        if (planeDAO == null) {
            planeDAO = new PlaneDAOImpl(connection);
        }
        return planeDAO;
    }

    public static DAO getBusDAO() {
        if (busDAO == null) {
            busDAO = new BusDAOImpl(connection);
        }
        return busDAO;
    }

    public static DAO getCityDAO() {
        if (cityDAO == null) {
            cityDAO = new CityDAOImpl(connection);
        }
        return cityDAO;
    }

    public static DAO getTrainDAO() {
        if (trainDAO == null) {
            trainDAO = new TrainDAOImpl(connection);
        }
        return trainDAO;
    }

    public static DAO getTrolleyDAO() {
        if (trolleyDAO == null) {
            trolleyDAO = new TrolleyDAOImpl(connection);
        }
        return trolleyDAO;
    }
}
