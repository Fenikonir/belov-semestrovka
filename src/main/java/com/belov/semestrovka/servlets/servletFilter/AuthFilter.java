package com.belov.semestrovka.servlets.servletFilter;

import com.belov.semestrovka.service.AuthService;
import com.belov.semestrovka.service.Responser;
import com.belov.semestrovka.servlets.Names;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = {Names.PROFILE_LINK, Names.EDIT_PROFILE_LINK, Names.FORUM_LINK, Names.BUSES_LINK, Names.PLANES_LINK, Names.TRAIN_LINK, Names.TROLLEYS_LINK})
public class AuthFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Responser responser = AuthService.isUserAuthed(servletRequest);
        if (responser.code == 200) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.sendRedirect(Names.AUTH_LINK);
        }
    }
}
