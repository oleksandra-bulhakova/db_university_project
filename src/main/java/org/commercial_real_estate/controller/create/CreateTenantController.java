package org.commercial_real_estate.controller.create;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Tenant;
import org.commercial_real_estate.repository.TenantDAO;
import org.commercial_real_estate.repository.impl.TenantDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/tenants/create-tenant")
public class CreateTenantController extends HttpServlet {

    private TenantDAO tenantDAO = new TenantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Tenant tenant = new Tenant();

        request.setAttribute("tenant", tenant);
        Map<Long, String> streets = tenantDAO.getStreets();
        request.setAttribute("streets", streets);
        Map<Long, String> sources = tenantDAO.getAllAcquisitionSources();
        request.setAttribute("sources", sources);
        Map<Long, String> districts = tenantDAO.getDistricts();
        request.setAttribute("districts", districts);
        Map<Long, String> buildingTypes = tenantDAO.getObjectTypes();
        request.setAttribute("buildingTypes", buildingTypes);

        request.getRequestDispatcher("/WEB-INF/views/create-tenant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Tenant tenant = new Tenant();

        tenant.setStreetId(Long.parseLong(request.getParameter("streetId")));
        tenant.setAcquisitionSourceId(Long.parseLong(request.getParameter("source")));
        tenant.setFirstName(request.getParameter("firstName"));
        tenant.setLastName(request.getParameter("lastName"));
        tenant.setMiddleName(request.getParameter("middleName"));
        tenant.setEmail(request.getParameter("email"));
        tenant.setPhone(request.getParameter("phone"));
        tenant.setAcquisitionDate(Date.valueOf(String.valueOf(request.getParameter("acquisitionDate"))));
        tenant.setBuildingNumber(request.getParameter("building"));
        tenant.setPremiseNumber(Integer.parseInt(request.getParameter("premise")));
        tenant.setDesiredObjectTypeId(Integer.parseInt(request.getParameter("objectType")));
        tenant.setDesiredDistrictId(Integer.parseInt(request.getParameter("district")));
        tenant.setBudget(Integer.parseInt(request.getParameter("budget")));
        tenant.setDesiredArea(Integer.parseInt(request.getParameter("area")));

        tenantDAO.createTenant(tenant);
        response.sendRedirect("/tenants");
    }
}
