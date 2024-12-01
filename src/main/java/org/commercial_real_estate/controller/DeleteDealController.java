package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.DealDAO;
import org.commercial_real_estate.repository.impl.DealDAOImpl;

import java.io.IOException;

@WebServlet("/deals/delete")
public class DeleteDealController extends HttpServlet {
    DealDAO dealDAO = new DealDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id = Long.parseLong(request.getParameter("id"));
        dealDAO.deleteDeal(id);
        response.sendRedirect("/deals");
    }
}
