package com.example.demo.database.repository;

import com.example.demo.database.PasswordEncryption;
import com.example.demo.database.dao.DAO;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.Article;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.User;
import com.example.demo.database.entity.UserFiles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.demo.database.dao.DAOFabric.connection;

public class PgRepository {
    private static final DAO userDAO = DAOFabric.getUserDAO();
    private static final DAO cityDAO = DAOFabric.getCityDAO();

    public static User auth(String email, String password) {
        User user = (User) userDAO.getByParameter("email", email);
        if (user != null && user.getPassword().equals(PasswordEncryption.encryptPassword(password))) {
            System.out.println(user.getUsername() + " " + user.getPassword() + " " + PasswordEncryption.encryptPassword(password));
            System.out.println(user.toString());
            return user;
        }
        System.out.println(user.getUsername() + " " + user.getPassword() + " " + PasswordEncryption.encryptPassword(password));
        System.out.println("Invalid Password");
        return null;
    }

    public static User getUserByEmail(String email) {
        return (User) userDAO.getByParameter("email", email);
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
        return (City) cityDAO.getByParameter("city_name", name);
    }

    public static List<Article> getAllPosts() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles WHERE type_name = 'пост'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = extractArticleFromResultSet(resultSet);
                System.out.println(String.format("Text: %s, author: %s, createdDate %s", article.getValue(), article.getAuthorId(), article.getCreatedDate()));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        articles.sort(Comparator.comparing(Article::getCreatedDate));
        return articles;
    }

    public static Article getBioForUser(int userId) {
        String query = "SELECT * FROM articles WHERE author = ? AND type_name = 'био'";
        System.out.println("PgRepository " + query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractArticleFromResultSet(resultSet);
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
        article.setCreatedDate(resultSet.getTimestamp("created_at").toLocalDateTime());
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
        System.out.println("PgRepository Avatars: filePath " + userFiles.getFile_path());
        return userFiles;
    }

    public static void saveUserAvatar(UserFiles newUserFiles) {
        System.out.println("Изменение аватара у пользователя на " + newUserFiles.getFile_path());
        if (getUserAvatar(newUserFiles.getUserId()).getFile_path() == null) {
            String query = "INSERT INTO files (user_id, file_path, file_type) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                System.out.println("PgRepository: save new avatar for " + newUserFiles.getUserId());
                statement.setInt(1, newUserFiles.getUserId());
                statement.setString(2, newUserFiles.getFile_path());
                statement.setString(3, newUserFiles.getFile_type());
                statement.executeUpdate();
                System.out.println("PgRepository: файл успешно загружен");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "UPDATE files SET file_path = ? WHERE user_id = ? AND file_type = 'avatar'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                System.out.println("Начало обновления аватара ");
                statement.setInt(2, newUserFiles.getUserId());
                statement.setString(1, newUserFiles.getFile_path());
                statement.executeUpdate();
                System.out.println("PgRepository: файл успешно обновлен");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveUserBio(Article article) {
        if (getBioForUser(article.getAuthorId()) == null) {
            System.out.println("Save new Bio");
            String query = "INSERT INTO articles (author, type_name, valuer) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, article.getAuthorId());
                statement.setString(2, "био");
                statement.setString(3, article.getValue());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Update current bio");
            String query = "UPDATE articles SET valuer = ? WHERE author = ? AND type_name = 'био'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(2, article.getAuthorId());
                statement.setString(1, article.getValue());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Article> getPostsByPage(int pageNumber, int pageSize) {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles WHERE type_name = 'пост' ORDER BY created_at LIMIT ? OFFSET ?";
        int offset = (pageNumber - 1) * pageSize;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
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

    public static int getTotalPosts() {
        String query = "SELECT COUNT(*) FROM articles WHERE type_name = 'пост'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}