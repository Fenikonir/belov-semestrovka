package com.belov.semestrovka.servlets.pages;

import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.entity.*;
import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.service.TransportService;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.servlets.Button;
import com.belov.semestrovka.servlets.Names;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(Names.PROFILE_LINK)
public class ProfilePage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {

            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.PROFILE_FILE);
            System.out.println((String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE));
            User user = PgRepository.getUserByEmail((String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE));
            Map<String, Object> dataModel = new HashMap<>();
            System.out.println("ProfilePage Avatar: " + user.getAvatar());
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("buttons", Button.getAuthButton());
            System.out.println("ProfilePage: " + user.getUsername());
            dataModel.put("user", user);
            dataModel.put("pageTitle", "Профиль");
            dataModel.put("lang", "en");

            List planes = DAOFabric.getPlaneTransportDAO().getByCity(user.getCity().getId());
            List trains = DAOFabric.getTrainTransportDAO().getByCity(user.getCity().getId());
            List trolleys = DAOFabric.getTrolleyTransportDAO().getByCity(user.getCity().getId());
            List buses = DAOFabric.getBusTransportDAO().getByCity(user.getCity().getId());
            System.out.println("PageProfile: " + String.valueOf(planes.size()) + " " + trains.size() + " " + trolleys.size() + " " + buses.size());
            dataModel.put("planes", planes.size());
            dataModel.put("planes_f", TransportService.calculateAverageFare(new Plane(), user.getCity()));
            dataModel.put("planes_s", TransportService.calculateAverageStops(new Plane(), user.getCity()));
            dataModel.put("trains", trains.size());
            dataModel.put("trains_f", TransportService.calculateAverageFare(new Train(), user.getCity()));
            dataModel.put("trains_s", TransportService.calculateAverageStops(new Train(), user.getCity()));
            dataModel.put("trolleys", trolleys.size());
            dataModel.put("trolleys_f", TransportService.calculateAverageFare(new Trolley(), user.getCity()));
            dataModel.put("trolleys_s", TransportService.calculateAverageStops(new Trolley(), user.getCity()));
            dataModel.put("buses", buses.size());
            dataModel.put("buses_f", TransportService.calculateAverageFare(new Bus(), user.getCity()));
            dataModel.put("buses_s", TransportService.calculateAverageStops(new Bus(), user.getCity()));
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }
    public void destroy() {
    }
}
