package com.example.demo.database.repository;

import com.example.demo.database.PasswordEncryption;
import com.example.demo.database.dao.DAO;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.database.dao.DAOFabric.connection;

public class PgRepository {
    private static DAO userDAO = DAOFabric.getUserDAO();
    private static DAO cityDAO = DAOFabric.getCityDAO();

    public static User auth(String email, String password) {
        User user = (User) userDAO.getByParameter("email", email);
        if (user.getPassword().equals(PasswordEncryption.encryptPassword(password))) {
            System.out.println(user.getUsername() + " " + user.getPassword() + " " + PasswordEncryption.encryptPassword(password));
            System.out.println(user.toString());
            return user;
        }
        System.out.println("Invalid Password");
        return null;
    }

    public static User getUserByEmail(String email) {
        User user = (User) userDAO.getByParameter("email", email);
        return user;
    }

    public static boolean haveUser(String email) {
        try {
            User user = (User) userDAO.getByParameter("email", email);
            System.out.println(user.getUsername() + " " + user.getPassword());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static City getCityByName(String name) {
        City city = (City) cityDAO.getByParameter("city_name", name);
        return city;
    }

    public static List<Article> getAllArticlesByUser(int userId) {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles WHERE author = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = extractArticleFromResultSet(resultSet);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static Article getBioForUser(int userId) {
        String query = "SELECT * FROM articles WHERE author = ? AND type_name = 'био'";
        System.out.println("PgRepository " + query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = extractArticleFromResultSet(resultSet);
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Article extractArticleFromResultSet(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setAuthorId(resultSet.getInt("author"));
        article.setType(resultSet.getString("type_name"));
        article.setValue(resultSet.getString("valuer"));

        return article;
    }

    public static UserFiles getUserAvatar(int userId) {
        UserFiles userFiles = new UserFiles();
        String query = "SELECT * FROM files WHERE user_id = ? AND file_type = 'avatar'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userFiles.setId(resultSet.getInt("id"));
                userFiles.setUserId(userId);
                userFiles.setFile_path(resultSet.getString("file_path"));
                userFiles.setFile_type(resultSet.getString("file_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("PgRepository Avatars: " + userFiles);
        return userFiles;
    }

    public static void saveUserAvatar(UserFiles newUserFiles) {
        if (getUserAvatar(newUserFiles.getUserId()) == null) {
            String query = "INSERT INTO files (user_id, file_path, file_type) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                System.out.println("PgRepository: save new avatar for " + newUserFiles.getUserId());
                statement.setInt(1, newUserFiles.getUserId());
                statement.setString(2, newUserFiles.getFile_path());
                statement.setString(3, newUserFiles.getFile_type());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "UPDATE files SET file_path = ? WHERE id = ? AND file_type = 'avatar'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(2, newUserFiles.getUserId());
                statement.setString(1, newUserFiles.getFile_path());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
