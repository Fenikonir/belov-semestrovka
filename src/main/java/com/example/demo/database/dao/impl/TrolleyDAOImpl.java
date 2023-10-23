package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.Trolley;
import com.example.demo.database.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrolleyDAOImpl implements DAO<Trolley> {
    private Connection connection;

    public TrolleyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Trolley getById(int id) {
        Trolley trolley = null;
        String query = "SELECT * FROM Trolleys WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trolley = new Trolley();
                trolley.setId(resultSet.getInt("id"));
                trolley.setTrolleyNumber(resultSet.getString("trolley_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                trolley.setCity(city);
                trolley.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trolley;
    }

    @Override
    public Trolley getByParameter(String p, String v) {
        Trolley trolley = null;
        String query = String.format("SELECT * FROM Trolleys WHERE %s = ?", p);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trolley = new Trolley();
                trolley.setId(resultSet.getInt("id"));
                trolley.setTrolleyNumber(resultSet.getString("trolley_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                trolley.setCity(city);
                trolley.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trolley;
    }

    @Override
    public List<Trolley> getAll() {
        List<Trolley> trolleyList = new ArrayList<>();
        String query = "SELECT * FROM Trolleys";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Trolley trolley = new Trolley();
                trolley.setId(resultSet.getInt("id"));
                trolley.setTrolleyNumber(resultSet.getString("trolley_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                trolley.setCity(city);
                trolley.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
                trolleyList.add(trolley);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trolleyList;
    }

    @Override
    public void save(Trolley entity) {
        String query = "INSERT INTO Trolleys (trolley_number, city_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTrolleyNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Trolley entity) {
        String query = "UPDATE Trolleys SET trolley_number = ?, city_id = ?, last_modified = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTrolleyNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Trolley entity) {
        String query = "DELETE FROM Trolleys WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}