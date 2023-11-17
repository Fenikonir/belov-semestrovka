package com.belov.semestrovka.servlets.pages;

import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.servlets.Names;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.database.entity.City;
import com.belov.semestrovka.database.entity.User;
import com.belov.semestrovka.servlets.Button;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(Names.EDIT_PROFILE_LINK)
public class EditProfilePage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {

            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.EDIT_PROFILE_FILE);
            User user = PgRepository.getUserByEmail((String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE));
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("buttons", Button.getAuthButton());
            System.out.println("ProfilePage: " + user.getUsername());
            dataModel.put("user", user);
            dataModel.put("pageTitle", "Редактирование Профиля");
            dataModel.put("lang", "en");
            List<City> cityList = DAOFabric.getCityDAO().getAll();
            List<String> cityNames = cityList.stream().map(City::getCityName).collect(Collectors.toList());
            dataModel.put("cities", cityNames);
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            User user = PgRepository.getUserByEmail((String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE));
            // Retrieve the data from the request parameters
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String city = request.getParameter("city");
            String birthday = request.getParameter("birthday");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(birthday, formatter);
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthday(date);
            user.setRole(role);
            user.setMobilePhone(phone);
            user.setCity(PgRepository.getCityByName(city));

            // Print the data to the console
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("City: " + city);
            System.out.println("Birthday: " + birthday);
            System.out.println("Role: " + role);
            System.out.println("Phone: " + phone);

            DAOFabric.getUserDAO().update(user);

            // Optionally, you can perform further processing or save the data to a database

            // Send a response back to the client
            response.getWriter().println("Data received successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error processing the request.");
        }
    }

    public void destroy() {
    }
}
