package com.example.demo.servletFilter;

import com.example.demo.database.repository.PgRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.Responser;
import com.example.demo.servlets.Names;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = {Names.PROFILE_LINK, Names.EDIT_PROFILE_LINK, Names.FORUM_LINK, "/vote", "/golos", "/uploaded", "/uploader"})
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
