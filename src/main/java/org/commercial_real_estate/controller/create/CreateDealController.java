package org.commercial_real_estate.controller.create;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.commercial_real_estate.model.entities.Deal;
import org.commercial_real_estate.repository.DealDAO;
import org.commercial_real_estate.repository.impl.DealDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/deals/create-deal")
public class CreateDealController extends HttpServlet {

    private DealDAO dealDAO = new DealDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Deal deal = new Deal();

        request.setAttribute("deal", deal);
        Map<Long, String> objects = dealDAO.getObjects();
        request.setAttribute("objects", objects);
        Map<Long, String> tenants = dealDAO.getTenants();
        request.setAttribute("tenants", tenants);
        Map<Long, String> statuses = dealDAO.getStatuses();
        request.setAttribute("statuses", statuses);
        Map<Long, String> realtors = dealDAO.getRealtors();
        request.setAttribute("realtors", realtors);

        request.getRequestDispatcher("/WEB-INF/views/create-deal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Deal deal = new Deal();

        deal.setDate(Date.valueOf(request.getParameter("dealDate")));
        deal.setDealStatusId(Integer.parseInt(request.getParameter("status")));
        deal.setRealtorId(Integer.parseInt(request.getParameter("realtor")));
        deal.setObjectId(Integer.parseInt(request.getParameter("object")));
        deal.setTenantId(Integer.parseInt(request.getParameter("tenant")));
        deal.setPrice(Integer.parseInt(request.getParameter("dealPrice")));

        dealDAO.createDeal(deal);
        response.sendRedirect("/deals");
    }
}
