package org.commercial_real_estate.controller.read;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Tenant;
import org.commercial_real_estate.repository.TenantDAO;
import org.commercial_real_estate.repository.impl.TenantDAOImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/tenants")
public class TenantController extends HttpServlet {

    private TenantDAO tenantDAO = new TenantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<Tenant> tenants;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            tenants = tenantDAO.searchByName(searchQuery);
            request.setAttribute("searchQuery", searchQuery);
        } else {
            tenants = tenantDAO.getAllTenants();
        }

        request.setAttribute("tenants", tenants);
        request.getRequestDispatcher("/WEB-INF/views/tenant-list.jsp").forward(request, response);
    }
}
