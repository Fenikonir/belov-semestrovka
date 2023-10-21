package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.Plane;
import com.example.demo.database.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAOImpl implements DAO<Plane> {
    private Connection connection;

    public PlaneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Plane getById(int id) {
        Plane plane = null;
        String query = "SELECT * FROM Planes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                plane = new Plane();
                plane.setId(resultSet.getInt("id"));
                plane.setPlaneNumber(resultSet.getString("plane_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                plane.setCity(city);
                plane.setLastModified(resultSet.getTimestamp("last_modified"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    @Override
    public List<Plane> getAll() {
        List<Plane> planeList = new ArrayList<>();
        String query = "SELECT * FROM Planes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Plane plane = new Plane();
                plane.setId(resultSet.getInt("id"));
                plane.setPlaneNumber(resultSet.getString("plane_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                plane.setCity(city);
                plane.setLastModified(resultSet.getTimestamp("last_modified"));
                planeList.add(plane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planeList;
    }

    @Override
    public void save(Plane entity) {
        String query = "INSERT INTO Planes (plane_number, city_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getPlaneNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Plane entity) {
        String query = "UPDATE Planes SET plane_number = ?, city_id = ?, last_modified = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getPlaneNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Plane entity) {
        String query = "DELETE FROM Planes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}