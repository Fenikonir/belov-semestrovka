package com.example.demo.servletFilter;

import com.example.demo.servlets.Names;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = {Names.profile, "/vote", "/calc", "/vote", "/golos", "/uploaded", "/uploader"})
public class AuthFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userAuthed = (String) servletRequest.getSession().getAttribute("username");

        if (userAuthed == null) {
            servletResponse.sendRedirect(Names.auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
