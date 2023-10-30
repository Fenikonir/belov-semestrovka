package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.UserFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilesDAOImpl implements DAO<UserFiles> {
    private final Connection connection;

    public FilesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserFiles getById(int id) {
        String query = "SELECT * FROM files WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUserFilesFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserFiles getByParameter(String p, String v) {
        String query = "SELECT * FROM files WHERE " + p + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUserFilesFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserFiles> getAll() {
        List<UserFiles> userFilesList = new ArrayList<>();
        String query = "SELECT * FROM files";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserFiles userFiles = extractUserFilesFromResultSet(resultSet);
                userFilesList.add(userFiles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFilesList;
    }

    @Override
    public void save(UserFiles entity) {
        String query = "INSERT INTO files (user_id, file_type, file_path) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getFile_type());
            statement.setString(3, entity.getFile_path());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserFiles entity) {
        String query = "UPDATE files SET user_id = ?, file_type = ?, file_path = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getFile_type());
            statement.setString(3, entity.getFile_path());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserFiles entity) {
        String query = "DELETE FROM files WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserFiles extractUserFilesFromResultSet(ResultSet resultSet) throws SQLException {
        UserFiles userFiles = new UserFiles();
        userFiles.setId(resultSet.getInt("id"));
        userFiles.setUserId(resultSet.getInt("user_id"));
        userFiles.setFile_type(resultSet.getString("file_type"));
        userFiles.setFile_path(resultSet.getString("file_path"));
        return userFiles;
    }
}