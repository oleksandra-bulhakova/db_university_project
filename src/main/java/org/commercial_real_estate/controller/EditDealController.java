package org.commercial_real_estate.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Deal;
import org.commercial_real_estate.repository.DealDAO;
import org.commercial_real_estate.repository.impl.DealDAOImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


@WebServlet("/edit-deal")
public class EditDealController extends HttpServlet {

    private final DealDAO dealDAO = new DealDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long dealId = Long.parseLong(request.getParameter("id"));
        Deal deal = dealDAO.getDealById(dealId);

        if (deal == null) {
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("deal", deal);
        Map<Long, String> objects = dealDAO.getObjects();
        request.setAttribute("objects", objects);
        Map<Long, String> tenants = dealDAO.getTenants();
        request.setAttribute("tenants", tenants);
        Map<Long, String> statuses = dealDAO.getStatuses();
        request.setAttribute("statuses", statuses);
        Map<Long, String> realtors = dealDAO.getRealtors();
        request.setAttribute("realtors", realtors);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/edit-deal.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long dealId = Long.parseLong(request.getParameter("id"));

        Deal deal = new Deal();
        deal.setId(dealId);
        deal.setDealStatusId(Integer.parseInt(request.getParameter("status")));
        deal.setDate(java.sql.Date.valueOf(request.getParameter("dealDate")));
        deal.setObjectId(Long.parseLong(request.getParameter("object")));
        deal.setRealtorId(Long.parseLong(request.getParameter("realtor")));
        deal.setPrice(Integer.parseInt(request.getParameter("dealPrice")));
        deal.setTenantId(Integer.parseInt(request.getParameter("tenant")));

        dealDAO.updateDeal(deal);
        response.sendRedirect("/deals");
    }
}
