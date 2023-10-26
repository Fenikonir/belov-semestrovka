package com.example.demo.servlets.pages;

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

@WebServlet(Names.CONTACT_LINK)
public class ContactPage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.CONTACT_FILE);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("host", Names.HOST_LINK);
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
