package com.belov.semestrovka.service;

import com.belov.semestrovka.database.PasswordEncryption;
import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.database.entity.City;
import com.belov.semestrovka.database.entity.User;
import com.belov.semestrovka.servlets.Names;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthService {
    public static Responser signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(request.getParameter(Names.COOKIES_AUTH_ATTRIBUTE));
        Boolean rememberMe = "on".equals(request.getParameter(Names.COOKIES_AUTH_ATTRIBUTE)) ? true : false;
        System.out.println("AuthPage " + "Remember me status: " + rememberMe + " " + email);
        User user;
        try {
            user = PgRepository.auth(email, password);
        } catch (NullPointerException nullPointerException) {
            return new Responser(404, "Пользователь с такими данными не обнаружен");
        }
        if (user != null) {
            session.setAttribute(Names.SESSION_AUTH_ATTRIBUTE, user.getEmail());
            if (rememberMe) {
                Cookie rememberMeCookie = new Cookie(Names.COOKIES_AUTH_ATTRIBUTE, email);
                rememberMeCookie.setMaxAge(86400 * 7); // Cookie expires in 24 hours * 7 day
                response.addCookie(rememberMeCookie);
            }
            return new Responser(200, "");
        } else {
            return new Responser(404, "Неверный логин или пароль, попробуйте ещё раз");
        }
    }

    public static Responser signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Process registration logic
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return new Responser(404, "Пароли должны совпадать");
        }

        // Check if user with the same email already exists
        User existingUser = PgRepository.getUserByEmail(email);
        if (existingUser != null) {
            return new Responser(404, "Пользователь с такой почтой уже зарегестирован! Используйте другую");
        }

        City city = PgRepository.getCityByName(request.getParameter("city"));
        User user = new User();
        System.out.println("AuthPage " + email + " " + password);
        user.setEmail(email);
        user.setPassword(PasswordEncryption.encryptPassword(password));
        user.setCity(city);
        DAOFabric.getUserDAO().save(user);

        HttpSession session = request.getSession();
        session.setAttribute(Names.SESSION_AUTH_ATTRIBUTE, email);
        return new Responser(200, "");
    }

    public static Responser isUserAuthed(HttpServletRequest request) {
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        if (userAuthed != null) {
            return new Responser(200, "");
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Names.COOKIES_AUTH_ATTRIBUTE)) {
                        System.out.println("AuthFilter " + "Cookies " + cookie.getValue());
                        if (PgRepository.haveUser(cookie.getValue())) {
                            request.getSession().setAttribute(Names.SESSION_AUTH_ATTRIBUTE, cookie.getValue());
                            return new Responser(200, "");
                        }
                        break;
                    }
                }
            }
        }
        return new Responser(401, "Пользователь не авторизован");
    }

}
