package org.commercial_real_estate.controller.read;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Deal;
import org.commercial_real_estate.repository.DealDAO;
import org.commercial_real_estate.repository.impl.DealDAOImpl;
import java.io.IOException;

import java.sql.Date;
import java.util.List;

@WebServlet("/deals")
public class DealController extends HttpServlet {

    private DealDAO dealDAO = new DealDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterType = request.getParameter("filterType");
        List<Deal> deals;

        if (filterType != null && !filterType.isEmpty()) {
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            deals = dealDAO.filterByDate(startDate, endDate);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
        } else {
            deals = dealDAO.getAllDeals();
        }

        request.setAttribute("deals", deals);
        request.getRequestDispatcher("/WEB-INF/views/deals.jsp").forward(request, response);
    }
}
