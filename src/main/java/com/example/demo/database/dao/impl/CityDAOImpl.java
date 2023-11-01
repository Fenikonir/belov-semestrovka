package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.dao.TransportDAO;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.Plane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements DAO<City> {
    private Connection connection;

    public CityDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public City getById(int id) {
        City city = null;
        String query = "SELECT * FROM Cities WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setBusCount(resultSet.getInt("bus_count"));
                city.setPlaneCount(resultSet.getInt("plane_count"));
                city.setTrolleyCount(resultSet.getInt("trolley_count"));
                city.setTrainCount(resultSet.getInt("train_count"));
                city.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City getByParameter(String p, String v) {
        City city = null;
        String query = String.format("SELECT * FROM Cities WHERE %s = ?", p);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setBusCount(resultSet.getInt("bus_count"));
                city.setPlaneCount(resultSet.getInt("plane_count"));
                city.setTrolleyCount(resultSet.getInt("trolley_count"));
                city.setTrainCount(resultSet.getInt("train_count"));
                city.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> cityList = new ArrayList<>();
        String query = "SELECT * FROM Cities";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setBusCount(resultSet.getInt("bus_count"));
                city.setPlaneCount(resultSet.getInt("plane_count"));
                city.setTrolleyCount(resultSet.getInt("trolley_count"));
                city.setTrainCount(resultSet.getInt("train_count"));
                city.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
                cityList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public void save(City entity) {
        String query = "INSERT INTO Cities (city_name, bus_count, plane_count, trolley_count, train_count) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getCityName());
            statement.setInt(2, entity.getBusCount());
            statement.setInt(3, entity.getPlaneCount());
            statement.setInt(4, entity.getTrolleyCount());
            statement.setInt(5, entity.getTrainCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(City entity) {
        String query = "UPDATE Cities SET city_name = ?, bus_count = ?, plane_count = ?, trolley_count = ?, " +
                "train_count = ?, last_modified = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getCityName());
            statement.setInt(2, entity.getBusCount());
            statement.setInt(3, entity.getPlaneCount());
            statement.setInt(4, entity.getTrolleyCount());
            statement.setInt(5, entity.getTrainCount());
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            statement.setInt(7, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(City entity) {
        String query = "DELETE FROM Cities WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}