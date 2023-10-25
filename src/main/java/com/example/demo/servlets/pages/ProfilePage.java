package com.example.demo.servlets.pages;

import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.User;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.servlets.Button;
import com.example.demo.servlets.Names;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Names.profile)
public class ProfilePage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("profile.ftl");
            User user = PgRepository.getUserByEmail((String) request.getSession().getAttribute("email"));
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("host", Names.host);
            dataModel.put("buttons", Button.getAuthButton());
            dataModel.put("user", user);
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void destroy() {
    }
}
