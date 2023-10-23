package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.Train;
import com.example.demo.database.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl implements DAO<Train> {
    private Connection connection;

    public TrainDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Train getById(int id) {
        Train train = null;
        String query = "SELECT * FROM Trains WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                train = new Train();
                train.setId(resultSet.getInt("id"));
                train.setTrainNumber(resultSet.getString("train_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                train.setCity(city);
                train.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }

    @Override
    public Train getByParameter(String p, String v) {
        Train train = null;
        String query = String.format("SELECT * FROM Trains WHERE %s = ?", p);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                train = new Train();
                train.setId(resultSet.getInt("id"));
                train.setTrainNumber(resultSet.getString("train_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                train.setCity(city);
                train.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }

    @Override
    public List<Train> getAll() {
        List<Train> trainList = new ArrayList<>();
        String query = "SELECT * FROM Trains";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Train train = new Train();
                train.setId(resultSet.getInt("id"));
                train.setTrainNumber(resultSet.getString("train_number"));
                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                train.setCity(city);
                train.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
                trainList.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainList;
    }

    @Override
    public void save(Train entity) {
        String query = "INSERT INTO Trains (train_number, city_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTrainNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Train entity) {
        String query = "UPDATE Trains SET train_number = ?, city_id = ?, last_modified = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTrainNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Train entity) {
        String query = "DELETE FROM Trains WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}