package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.Article;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.User;
import com.example.demo.database.repository.PgRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements DAO<User> {
    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByParameter(String param, String value) {
        User user = null;
        String query = String.format("SELECT * FROM users WHERE %s = ?", param);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UserDaoImpl: " + "getUser" + user.toString());
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO users (username, email, password, city_id, creation_date, last_modified, first_name, last_name, birthday, role, mobile_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getCity().getId());
            statement.setObject(5, entity.getCreationDate());
            statement.setObject(6, entity.getLastModified());
            statement.setString(7, entity.getFirstName());
            statement.setString(8, entity.getLastName());
            statement.setObject(9, entity.getBirthday());
            statement.setString(10, entity.getRole());
            statement.setString(11, entity.getMobilePhone());
            DAO< Article> articleDAO = DAOFabric.getArticleDAO();
            articleDAO.save(entity.getBio());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, city_id = ?, creation_date = ?, last_modified = ?, first_name = ?, last_name = ?, birthday = ?, role = ?, mobile_phone = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setInt(4, entity.getCity().getId());
            statement.setObject(5, entity.getCreationDate());
            statement.setObject(6, entity.getLastModified());
            statement.setString(7, entity.getFirstName());
            statement.setString(8, entity.getLastName());
            statement.setObject(9, entity.getBirthday());
            statement.setString(10, entity.getRole());
            statement.setString(11, entity.getMobilePhone());
            statement.setInt(12, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User entity) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        City city = (City) DAOFabric.getCityDAO().getById(resultSet.getInt("city_id"));
        user.setCity(city);
        user.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        user.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
        user.setBio(PgRepository.getBioForUser(user.getId()));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthday(resultSet.getDate("birthday").toLocalDate());
        user.setRole(resultSet.getString("role"));
        user.setMobilePhone(resultSet.getString("mobile_phone"));
        return user;
    }
}