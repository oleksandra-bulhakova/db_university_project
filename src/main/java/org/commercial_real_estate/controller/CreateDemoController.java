package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Demonstration;
import org.commercial_real_estate.repository.DemonstrationDAO;
import org.commercial_real_estate.repository.impl.DemonstrationDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/demonstrations/create-demo")
public class CreateDemoController extends HttpServlet {

    private DemonstrationDAO demonstrationDAO = new DemonstrationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Demonstration demonstration = new Demonstration();

        request.setAttribute("demonstration", demonstration);
        Map<Long, String> objects = demonstrationDAO.getObjects();
        request.setAttribute("objects", objects);
        Map<Long, String> statuses = demonstrationDAO.getStatuses();
        request.setAttribute("statuses", statuses);
        Map<Long, String> tenants = demonstrationDAO.getTenants();
        request.setAttribute("tenants", tenants);
        Map<Long, String> realtors = demonstrationDAO.getRealtors();
        request.setAttribute("realtors", realtors);

        request.getRequestDispatcher("/WEB-INF/views/create-demo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Demonstration demonstration = new Demonstration();
        demonstration.setDate(Date.valueOf(String.valueOf(request.getParameter("demoDate"))));
        demonstration.setDemoStatusId(Long.parseLong(request.getParameter("status")));
        demonstration.setRealtorId(Long.parseLong(request.getParameter("realtor")));
        demonstration.setTenantId(Long.parseLong(request.getParameter("tenant")));
        demonstration.setObjectId(Long.parseLong(request.getParameter("object")));

        demonstrationDAO.createDemo(demonstration);
        response.sendRedirect("/demonstrations");
    }
}
