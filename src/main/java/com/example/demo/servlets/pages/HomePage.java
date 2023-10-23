package com.example.demo.servlets.pages;

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

@WebServlet("")
public class HomePage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("index.ftl");
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("host", "http://localhost:8080/");
            dataModel.put("auth_link", "auth");
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void destroy() {
    }
}
