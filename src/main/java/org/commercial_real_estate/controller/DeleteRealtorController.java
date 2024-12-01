package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.RealtorDAO;
import org.commercial_real_estate.repository.impl.RealtorDAOImpl;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/realtors/delete")
public class DeleteRealtorController extends HttpServlet {
    RealtorDAO realtorDAO = new RealtorDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id = Long.parseLong(request.getParameter("id"));

        try {
            realtorDAO.deleteRealtor(id);
        } catch (SQLIntegrityConstraintViolationException s) {
            request.setAttribute("errorMessage", "Неможливо видалити рієлтора, бо він використовується. Перевірте угоди та покази.");
            request.setAttribute("url", "/realtors");
            request.getRequestDispatcher("/WEB-INF/views/deletion-constraint-error.jsp").forward(request, response);
        }
        response.sendRedirect("/realtors");
    }
}
