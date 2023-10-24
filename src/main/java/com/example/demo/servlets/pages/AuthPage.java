package com.example.demo.servlets.pages;

import com.example.demo.database.PasswordEncryption;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.User;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.servlets.Names;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(Names.auth)
public class AuthPage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("auth.ftl");
            List<City> cityList = DAOFabric.getCityDAO().getAll();
            Map<String, Object> dataModel = new HashMap<>();
            List<String> cityNames = cityList.stream().map(City::getCityName).collect(Collectors.toList());
            dataModel.put("cities", cityNames);
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            response.sendRedirect(Names.profile);
            return;
        }
        try {
            // Retrieve the page parameter from the request
            String page = request.getParameter("page");
            if (page != null) {
                if (page.equals("auth")) {
                    // Process authentication logic
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    User user;
                    try {
                        user = PgRepository.auth(email, password);
                    } catch (NullPointerException nullPointerException) {
                        response.getWriter().println("Пользователь с такими данными не обнаружен");
                        return;
                    }
                    if (user != null) {
                        session.setAttribute("username", user.getUsername());
                        response.sendRedirect(Names.profile);
                    } else {
                        response.getWriter().println("Ошибка в введенных данных");
                        return;
                    }
                    // Perform authentication logic using the email and password
                    // Redirect to /home
                } else if (page.equals("signup")) {
                    // Process registration logic
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirmPassword");

                    if (!password.equals(confirmPassword)) {
                        response.getWriter().println("Пароли должны совпадать");
                        return; // Stop further processing
                    }


                    City city = PgRepository.getCityByName(request.getParameter("city"));
                    User user = new User();
                    System.out.println(email + " " + password);
                    user.setUsername(email);
                    user.setPassword(PasswordEncryption.encryptPassword(password));
                    user.setCity(city);
                    connection = DriverManager.getConnection(DAOFabric.url, DAOFabric.username, DAOFabric.password);
                    DAOFabric.getUserDAO().save(user);
                    session.setAttribute("username", email);
                    // Perform registration logic using the email and password
                    // Redirect to /home
                    response.sendRedirect(Names.profile);
                }
            } else {
                // Invalid page parameter
                response.getWriter().println("Invalid page parameter.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка исключения
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Обработка исключения
                }
            }
        }
    }

    public void destroy() {
    }

    public void isSignIn() {
    }

    public void isSignUp() {
    }
}
