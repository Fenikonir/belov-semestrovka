package com.example.demo.servlets.pages;

import com.example.demo.database.PasswordEncryption;
import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.City;
import com.example.demo.database.entity.User;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.servlets.Button;
import com.example.demo.servlets.Names;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(Names.AUTH_LINK)
public class AuthPage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        if (userAuthed != null) {
            response.sendRedirect(Names.PROFILE_LINK);
        }
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.AUTH_FILE);
            List<City> cityList = DAOFabric.getCityDAO().getAll();
            Map<String, Object> dataModel = new HashMap<>();
            List<String> cityNames = cityList.stream().map(City::getCityName).collect(Collectors.toList());
            dataModel.put("cities", cityNames);
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("buttons", Button.getNonAuthButton());
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        if (username != null) {
            response.sendRedirect(Names.PROFILE_LINK);
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
                    Boolean rememberMe = "on".equals(request.getParameter(Names.COOKIES_AUTH_ATTRIBUTE)) ? true : false;
                    System.out.println("AuthPage" + "Remember me status: " + rememberMe);
                    User user;
                    try {
                        user = PgRepository.auth(email, password);
                    } catch (NullPointerException nullPointerException) {
                        response.getWriter().println("Пользователь с такими данными не обнаружен");
                        return;
                    }
                    if (user != null) {
                        session.setAttribute(Names.SESSION_AUTH_ATTRIBUTE, user.getUsername());
                        if (rememberMe) {
                            Cookie rememberMeCookie = new Cookie(Names.COOKIES_AUTH_ATTRIBUTE, email);
                            rememberMeCookie.setMaxAge(86400 * 7); // Cookie expires in 24 hours * 7 day
                            response.addCookie(rememberMeCookie);
                        }
                        response.sendRedirect(Names.PROFILE_LINK);
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
                    System.out.println("AuthPage" + email + " " + password);
                    user.setEmail(email);
                    user.setPassword(PasswordEncryption.encryptPassword(password));
                    user.setCity(city);
                    connection = DriverManager.getConnection(DAOFabric.url, DAOFabric.username, DAOFabric.password);
                    DAOFabric.getUserDAO().save(user);
                    session.setAttribute(Names.SESSION_AUTH_ATTRIBUTE, email);
                    // Perform registration logic using the email and password
                    // Redirect to /home
                    response.sendRedirect(Names.PROFILE_LINK);
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
