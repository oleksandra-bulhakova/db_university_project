package org.commercial_real_estate.controller.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.repository.TenantDAO;
import org.commercial_real_estate.repository.impl.TenantDAOImpl;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/tenants/delete")
public class DeleteTenantController extends HttpServlet {
    TenantDAO tenantDAO = new TenantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id = Long.parseLong(request.getParameter("id"));

        try {
            tenantDAO.deleteTenant(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            request.setAttribute("errorMessage", "Неможливо видалити орендаря, бо він використовується. Перевірте угоди та покази.");
            request.setAttribute("url", "/tenants");
            request.getRequestDispatcher("/WEB-INF/views/deletion-constraint-error.jsp").forward(request, response);
        }
        response.sendRedirect("/tenants");
    }
}
