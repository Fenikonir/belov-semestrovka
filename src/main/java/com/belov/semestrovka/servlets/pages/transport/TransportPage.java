package com.belov.semestrovka.servlets.pages.transport;

import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.entity.Plane;
import com.belov.semestrovka.database.entity.Transport;
import com.belov.semestrovka.database.entity.TransportStop;
import com.belov.semestrovka.database.entity.User;
import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.service.TransportService;
import com.belov.semestrovka.servlets.Button;
import com.belov.semestrovka.servlets.Names;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(Names.TRANSPORT_LINK)
public class TransportPage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("Error processing Freemarker template.");
    }

    public void preDoGet(HttpServletRequest request, HttpServletResponse response, Transport transport) throws IOException {
        response.setContentType("text/html");
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        User user = PgRepository.getUserByEmail(userAuthed);
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.TRANSPORT_FILE);
            Map<String, Object> dataModel = new HashMap<>();
            List<Transport> transportList = TransportService.getAllTransportForCity(transport, user.getCity());

            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("transport_name", transport.getMatName());
            dataModel.put("fare_p", (int) TransportService.calculateAverageFarePercentage(transport, user.getCity()));
            dataModel.put("fare", TransportService.calculateAverageFare(transport, user.getCity()));
            dataModel.put("stops", TransportService.calculateAverageStops(transport, user.getCity()));
            dataModel.put("stops_p", (int) TransportService.calculateAverageStopsPercentage(transport, user.getCity()));
            dataModel.put("transport_kol", transportList.size());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));
            dataModel.put("latest_add", TransportService.findTransportWithLatestCreatedAt(transportList).getCreatedAt().format(formatter));

            int[] types = TransportService.getTransportType(transport, user.getCity());
            System.out.println(types[0] + " " + types[1] + " " + types[2]);
            dataModel.put("regular", types[0]);
            dataModel.put("ordered", types[1]);
            dataModel.put("personal", types[2]);
            dataModel.put("stopsList", TransportService.getTransportStops(transportList));
            dataModel.put("transportList", transportList);
            Map<String, Integer> monthDict = TransportService.getMonthDictionary(transportList);
            for (Map.Entry<String, Integer> entry : monthDict.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                dataModel.put(key, value);
            }
            if (userAuthed != null) {
                dataModel.put("buttons", Button.getAuthButton());
            } else {
                dataModel.put("buttons", Button.getNonAuthButton());
            }
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void destroy() {
    }
}
