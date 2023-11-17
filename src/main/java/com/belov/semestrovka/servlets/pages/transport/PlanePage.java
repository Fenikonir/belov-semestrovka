package com.belov.semestrovka.servlets.pages.transport;

import com.belov.semestrovka.database.entity.Bus;
import com.belov.semestrovka.database.entity.Plane;
import com.belov.semestrovka.database.entity.Transport;
import com.belov.semestrovka.servlets.Names;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(Names.PLANES_LINK)
public class PlanePage extends TransportPage {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Transport transport = new Plane();
        preDoGet(request, response, transport);
    }
}
