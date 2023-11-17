package com.belov.semestrovka.service;

import com.belov.semestrovka.servlets.Names;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Responser {
    public int code;
    public String value;
    public Responser(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("ExceptionCode", String.valueOf(code));
        request.getSession().setAttribute("ExceptionValue", value);
        response.sendRedirect(Names.EXCEPTION_LINK);
    }
}
