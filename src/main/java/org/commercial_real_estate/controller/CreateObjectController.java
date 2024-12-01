package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.RealEstateObject;
import org.commercial_real_estate.repository.RealEstateObjectDAO;
import org.commercial_real_estate.repository.impl.RealEstateObjectDAOImpl;

import java.io.IOException;
import java.util.Map;

@WebServlet("/real-estate-objects/create")
public class CreateObjectController extends HttpServlet {

    private RealEstateObjectDAO realEstateObjectDAO = new RealEstateObjectDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RealEstateObject realEstateObject = new RealEstateObject();
        request.setAttribute("realEstateObject", realEstateObject);
        Map<Long, String> streets = realEstateObjectDAO.getStreets();
        request.setAttribute("streets", streets);
        Map<Long, String> buildingTypes = realEstateObjectDAO.getObjectTypes();
        request.setAttribute("buildingTypes", buildingTypes);
        Map<Long, String> owners = realEstateObjectDAO.getOwners();
        request.setAttribute("owners", owners);
        request.getRequestDispatcher("/WEB-INF/views/create-object.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RealEstateObject realEstateObject = new RealEstateObject();

        realEstateObject.setStreetId(Long.parseLong(request.getParameter("streetId")));
        realEstateObject.setArea(Integer.parseInt(request.getParameter("area")));
        realEstateObject.setFloor(Integer.parseInt(request.getParameter("floor")));
        realEstateObject.setRenovation(Boolean.parseBoolean(request.getParameter("renovation")));
        realEstateObject.setFurniture(Boolean.parseBoolean(request.getParameter("furniture")));
        realEstateObject.setPrice(Integer.parseInt(request.getParameter("price")));
        realEstateObject.setOwnerId(Long.parseLong(request.getParameter("owner")));
        realEstateObject.setObjectTypeId(Long.parseLong(request.getParameter("objectType")));
        realEstateObject.setBuildingNumber(request.getParameter("building"));
        realEstateObject.setPremiseNumber(Integer.parseInt(request.getParameter("premise")));

        realEstateObjectDAO.createObject(realEstateObject);
        response.sendRedirect("/real-estate-objects");
    }
}
