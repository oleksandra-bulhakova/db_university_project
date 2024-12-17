package org.commercial_real_estate.controller.edit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Tenant;
import org.commercial_real_estate.repository.TenantDAO;
import org.commercial_real_estate.repository.impl.TenantDAOImpl;

import java.io.IOException;
import java.util.Map;

@WebServlet("/edit-tenant")
public class EditTenantController extends HttpServlet {

    private final TenantDAO tenantDAO = new TenantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long tenantId = Long.parseLong(request.getParameter("id"));
        Tenant tenant = tenantDAO.getTenantById(tenantId);

        if (tenant == null) {
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("tenant", tenant);

        Map<Long, String> streets = tenantDAO.getStreets();
        request.setAttribute("streets", streets);
        Map<Long, String> sources = tenantDAO.getAllAcquisitionSources();
        request.setAttribute("sources", sources);
        Map<Long, String> districts = tenantDAO.getDistricts();
        request.setAttribute("districts", districts);
        Map<Long, String> buildingTypes = tenantDAO.getObjectTypes();
        request.setAttribute("buildingTypes", buildingTypes);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/edit-tenant.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long tenantId = Long.parseLong(request.getParameter("id"));
        Tenant tenant = new Tenant();
        tenant.setId(tenantId);
        tenant.setFirstName(request.getParameter("firstName"));
        tenant.setLastName(request.getParameter("lastName"));
        tenant.setEmail(request.getParameter("email"));
        tenant.setMiddleName(request.getParameter("middleName"));
        tenant.setPhone(request.getParameter("phone"));
        tenant.setAcquisitionSourceId(Long.parseLong(request.getParameter("source")));
        tenant.setDesiredDistrictId(Long.parseLong(request.getParameter("district")));
        tenant.setDesiredObjectTypeId(Long.parseLong(request.getParameter("objectType")));
        tenant.setStreetId(Long.parseLong(request.getParameter("streetId")));
        tenant.setBuildingNumber((request.getParameter("building")));
        tenant.setBudget(Integer.parseInt(request.getParameter("budget")));
        tenant.setAcquisitionDate(java.sql.Date.valueOf(request.getParameter("acquisitionDate")));
        tenant.setDesiredArea(Integer.parseInt(request.getParameter("area")));
        tenant.setPremiseNumber(Integer.parseInt(request.getParameter("premise")));

        tenantDAO.updateTenant(tenant);
        response.sendRedirect("/tenants");
    }

}
