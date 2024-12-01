package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Owner;
import org.commercial_real_estate.repository.OwnerDAO;
import org.commercial_real_estate.repository.impl.OwnerDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/owners/create-owner")
public class CreateOwnerController extends HttpServlet {

    private OwnerDAO ownerDAO = new OwnerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Owner owner = new Owner();
        request.setAttribute("owner", owner);
        List<Map<String, Object>> streets = ownerDAO.getAllStreets();
        List<Map<String, Object>> acquisitionSources = ownerDAO.getAllAcquisitionSources();
        request.setAttribute("streets", streets);
        request.setAttribute("acquisitionSources", acquisitionSources);
        request.getRequestDispatcher("/WEB-INF/views/create-owner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Owner owner = new Owner();

        owner.setStreetId(Long.parseLong(request.getParameter("streetId")));
        owner.setAcquisitionSourceId(Long.parseLong(request.getParameter("acquisitionSourceId")));
        owner.setFirstName(request.getParameter("firstName"));
        owner.setLastName(request.getParameter("lastName"));
        owner.setMiddleName(request.getParameter("middleName"));
        owner.setPhone(request.getParameter("phone"));
        owner.setEmail(request.getParameter("email"));
        owner.setAcquisitionDate(Date.valueOf(request.getParameter("acquisitionDate")));
        owner.setBuildingNumber(request.getParameter("buildingNumber"));
        owner.setPremiseNumber(Integer.parseInt(request.getParameter("premiseNumber")));

        ownerDAO.createOwner(owner);
        response.sendRedirect("/owners");
    }
}
