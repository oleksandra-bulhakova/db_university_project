package org.commercial_real_estate.controller.edit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Demonstration;
import org.commercial_real_estate.repository.DemonstrationDAO;
import org.commercial_real_estate.repository.impl.DemonstrationDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/edit-demo")
public class EditDemonstrationController extends HttpServlet {

    private final DemonstrationDAO demonstrationDAO = new DemonstrationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long demoId = Long.parseLong(request.getParameter("id"));
        Demonstration demonstration = demonstrationDAO.getDemoById(demoId);

        if (demonstration == null) {
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("demonstration", demonstration);
        Map<Long, String> objects = demonstrationDAO.getObjects();
        request.setAttribute("objects", objects);
        Map<Long, String> statuses = demonstrationDAO.getStatuses();
        request.setAttribute("statuses", statuses);
        Map<Long, String> tenants = demonstrationDAO.getTenants();
        request.setAttribute("tenants", tenants);
        Map<Long, String> realtors = demonstrationDAO.getRealtors();
        request.setAttribute("realtors", realtors);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/edit-demo.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long demoId = Long.parseLong(request.getParameter("id"));

        Demonstration demonstration = new Demonstration();
        demonstration.setId(demoId);
        demonstration.setRealtorId(Long.parseLong(request.getParameter("realtor")));
        demonstration.setDate(Date.valueOf(request.getParameter("demoDate")));
        demonstration.setObjectId(Long.parseLong(request.getParameter("object")));
        demonstration.setTenantId(Long.parseLong(request.getParameter("tenant")));
        demonstration.setDemoStatusId(Long.parseLong(request.getParameter("status")));

        demonstrationDAO.updateDemo(demonstration);
        response.sendRedirect("/demonstrations");
    }
}
