package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.Bus;
import com.example.demo.database.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAOImpl implements DAO<Bus> {
    private Connection connection;

    public BusDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bus getById(int id) {
        Bus bus = null;
        String query = "SELECT * FROM Buses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bus = new Bus();
                bus.setId(resultSet.getInt("id"));
                bus.setBusNumber(resultSet.getString("bus_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                bus.setCity(city);
                bus.setLastModified(resultSet.getTimestamp("last_modified"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

    @Override
    public List<Bus> getAll() {
        List<Bus> busList = new ArrayList<>();
        String query = "SELECT * FROM Buses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Bus bus = new Bus();
                bus.setId(resultSet.getInt("id"));
                bus.setBusNumber(resultSet.getString("bus_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                bus.setCity(city);
                bus.setLastModified(resultSet.getTimestamp("last_modified"));
                busList.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busList;
    }

    @Override
    public void save(Bus entity) {
        String query = "INSERT INTO Buses (bus_number, city_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getBusNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Bus entity) {
        String query = "UPDATE Buses SET bus_number = ?, city_id = ?, last_modified = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getBusNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Bus entity) {
        String query = "DELETE FROM Buses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}