package com.belov.semestrovka.servlets.pages;

import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.servlets.Button;
import com.belov.semestrovka.servlets.Names;
import com.belov.semestrovka.servlets.VRImages;
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
        response.setContentType("text/html; charset=UTF-8");
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.HOME_FILE);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("vr_image_1", VRImages.getRandomVRImages());
            dataModel.put("vr_image_2", VRImages.getRandomVRImages());
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
