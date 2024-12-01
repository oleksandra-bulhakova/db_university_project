package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.DemonstrationDAO;
import org.commercial_real_estate.repository.impl.DemonstrationDAOImpl;

import java.io.IOException;

@WebServlet("/demonstrations/delete")
public class DeleteDemoController extends HttpServlet {
    DemonstrationDAO demonstrationDAO = new DemonstrationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id = Long.parseLong(request.getParameter("id"));
        demonstrationDAO.deleteDemo(id);
        response.sendRedirect("/demonstrations");
    }
}
