package org.commercial_real_estate.controller;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/edit-object")
public class EditObjectController extends HttpServlet {

    private final RealEstateObjectDAO realEstateObjectDAO = new RealEstateObjectDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long objectId = Long.parseLong(request.getParameter("id"));
        RealEstateObject realEstateObject = realEstateObjectDAO.getRealEstateObjectById(objectId);

        if (realEstateObject == null) {
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("object", realEstateObject);
        Map<Long, String> streets = realEstateObjectDAO.getStreets();
        request.setAttribute("streets", streets);
        Map<Long, String> buildingTypes = realEstateObjectDAO.getObjectTypes();
        request.setAttribute("buildingTypes", buildingTypes);
        Map<Long, String> owners = realEstateObjectDAO.getOwners();
        request.setAttribute("owners", owners);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/edit-object.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long objectId = Long.parseLong(request.getParameter("id"));
        RealEstateObject realEstateObject = new RealEstateObject();

        realEstateObject.setId(objectId);
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

        realEstateObjectDAO.updateObject(realEstateObject);
        response.sendRedirect("/real-estate-objects");
    }


}
