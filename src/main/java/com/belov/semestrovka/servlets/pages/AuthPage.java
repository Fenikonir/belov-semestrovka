package com.belov.semestrovka.servlets.pages;

import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.service.AuthService;
import com.belov.semestrovka.service.Responser;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.database.entity.City;
import com.belov.semestrovka.servlets.Button;
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
        Responser responser = AuthService.isUserAuthed(request);
        if (responser.code == 200) {
            response.sendRedirect(Names.PROFILE_LINK);
            return;
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
            new Responser(500, "Ошибка на стороне сервера, ведутся работы по её устранению").setException(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String emailA = (String) session.getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        if (emailA != null) {
            response.sendRedirect(Names.PROFILE_LINK);
            return;
        }
        String page = request.getParameter("page");
        if (page != null) {
            Responser responser = (page.equals("auth")) ? AuthService.signin(request, response) : AuthService.signup(request, response);
            if (responser.code == 200) {
                response.sendRedirect(Names.PROFILE_LINK);
            } else {
                responser.setException(request, response);
            }
        } else {
            new Responser(404, "Ошибка в переданных данных, попробуйте еще раз").setException(request, response);
        }
    }

    public void destroy() {
    }
}
