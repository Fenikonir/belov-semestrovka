package com.example.demo.servlets.pages;

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

@WebServlet(Names.PROFILE_LINK)
public class ProfilePage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {

            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.PROFILE_FILE);
            User user = PgRepository.getUserByEmail((String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE));
            Map<String, Object> dataModel = new HashMap<>();
            System.out.println("ProfilePage Avatar: " + user.getAvatar());
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("buttons", Button.getAuthButton());
            System.out.println("ProfilePage: " + user.getUsername());
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
