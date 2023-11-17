package com.belov.semestrovka.servlets.pages;

import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.servlets.Names;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Names.EXCEPTION_LINK)
public class ExceptionPage extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String exceptionCode = (String) session.getAttribute("ExceptionCode");
        if (exceptionCode == null) {
            exceptionCode = "404";
        }
        String exceptionValue = (String) session.getAttribute("ExceptionValue");
        if (exceptionValue == null) {
            exceptionValue = "Неизвестная ошибка, вернитесь на предыдущую страницу";
        }
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.EXCEPTION_FILE);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("exception_code", exceptionCode);
            dataModel.put("exception_value", exceptionValue);
            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void destroy() {
    }
}
