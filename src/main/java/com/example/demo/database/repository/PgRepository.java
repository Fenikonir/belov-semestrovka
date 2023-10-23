package com.example.demo.database.repository;

import com.example.demo.database.PasswordEncryption;
import com.example.demo.database.dao.DAO;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.User;

public class PgRepository {
    private static DAO userDAO = DAOFabric.getUserDAO();
    private static DAO cityDAO = DAOFabric.getCityDAO();

    public static User auth(String email, String password) {
        User user = (User) userDAO.getByParameter("username", email);
        System.out.println(user.getUsername() + " " + user.getPassword() + " " + PasswordEncryption.encryptPassword(password));
        if (user.getPassword().equals(PasswordEncryption.encryptPassword(password))) {
            return user;
        } return null;
    }

    public static City getCityByName(String name) {
        City city = (City) cityDAO.getByParameter("city_name", name);
        return city;
    }
}
