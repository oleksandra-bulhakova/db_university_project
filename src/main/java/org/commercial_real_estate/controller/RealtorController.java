package org.commercial_real_estate.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Realtor;
import org.commercial_real_estate.repository.RealtorDAO;
import org.commercial_real_estate.repository.impl.RealtorDAOImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/realtors")
public class RealtorController extends HttpServlet {

    private RealtorDAO realtorDAO = new RealtorDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Realtor> realtors = realtorDAO.getAllRealtors();
        request.setAttribute("realtors", realtors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/realtors.jsp");
        dispatcher.forward(request, response);
    }
}
