package com.example.demo.database.dao.impl;

import com.example.demo.database.dao.DAO;
import com.example.demo.database.entity.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements DAO<Article> {

    private final Connection connection;

    public ArticleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Article getById(int id) {
        String query = "SELECT * FROM articles WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractArticleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Article getByParameter(String p, String v) {
        String query = "SELECT * FROM articles WHERE " + p + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractArticleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

    @Override
    public void save(Article entity) {
        String query = "INSERT INTO articles (author, type_name, valuer) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getAuthorId());
            statement.setString(2, entity.getType());
            statement.setString(3, entity.getValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Article entity) {
        String query = "UPDATE articles SET author = ?, type_name = ?, valuer = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getAuthorId());
            statement.setString(2, entity.getType());
            statement.setString(3, entity.getValue());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Article entity) {
        String query = "DELETE FROM articles WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Article extractArticleFromResultSet(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setAuthorId(resultSet.getInt("author"));
        article.setType(resultSet.getString("type_name"));
        article.setValue(resultSet.getString("valuer"));
        article.setCreatedDate(resultSet.getTimestamp("created_at").toLocalDateTime());

        return article;
    }
}
