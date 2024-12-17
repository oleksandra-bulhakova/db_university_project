package org.commercial_real_estate.controller.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.RealEstateObjectDAO;
import org.commercial_real_estate.repository.impl.RealEstateObjectDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/real-estate-objects/delete")
public class DeleteObjectController extends HttpServlet {

    private RealEstateObjectDAO realEstateObjectDAO = new RealEstateObjectDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            realEstateObjectDAO.deleteObjectByd(id);
        } catch (SQLIntegrityConstraintViolationException s) {
                request.setAttribute("errorMessage", "Неможливо видалити об'єкт, бо він використовується. Перевірте угоди та покази.");
                request.setAttribute("url", "/real-estate-objects");
                request.getRequestDispatcher("/WEB-INF/views/deletion-constraint-error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format: " + request.getParameter("id"));
            response.sendRedirect(request.getContextPath() + "/real-estate-objects");
        } catch (SQLException m) {
            m.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/real-estate-objects");
    }
}
