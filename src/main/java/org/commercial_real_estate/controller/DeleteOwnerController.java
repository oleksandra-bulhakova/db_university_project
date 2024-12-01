package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.OwnerDAO;
import org.commercial_real_estate.repository.impl.OwnerDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/owners/delete")
public class DeleteOwnerController extends HttpServlet {

    OwnerDAO ownerDAO = new OwnerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            ownerDAO.deleteOwner(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            request.setAttribute("errorMessage", "Неможливо видалити власника, бо він використовується. Перевірте об'єкти нерухомості.");
            request.setAttribute("url", "/owners");
            request.getRequestDispatcher("/WEB-INF/views/deletion-constraint-error.jsp").forward(request, response);
        }
        response.sendRedirect("/owners");
    }
}
