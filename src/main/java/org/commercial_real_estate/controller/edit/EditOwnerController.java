package org.commercial_real_estate.controller.edit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Owner;
import org.commercial_real_estate.repository.OwnerDAO;
import org.commercial_real_estate.repository.impl.OwnerDAOImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/edit-owner")
public class EditOwnerController extends HttpServlet {

    private OwnerDAO ownerDAO = new OwnerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Owner owner = ownerDAO.getOwnerById(id);

        List<Map<String, Object>> streets = ownerDAO.getAllStreets();
        List<Map<String, Object>> acquisitionSources = ownerDAO.getAllAcquisitionSources();

        request.setAttribute("owner", owner);
        request.setAttribute("streets", streets);
        request.setAttribute("acquisitionSources", acquisitionSources);
        request.getRequestDispatcher("/WEB-INF/views/edit-owner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String buildingNumber = request.getParameter("buildingNumber");
        int premiseNumber = Integer.parseInt(request.getParameter("premiseNumber"));
        long streetId = Long.parseLong(request.getParameter("streetId"));
        long acquisitionSourceId = Long.parseLong(request.getParameter("acquisitionSourceId"));
        java.sql.Date acquisitionDate = java.sql.Date.valueOf(request.getParameter("acquisitionDate"));

        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setMiddleName(middleName);
        owner.setPhone(phone);
        owner.setEmail(email);
        owner.setBuildingNumber(buildingNumber);
        owner.setPremiseNumber(premiseNumber);
        owner.setStreetId(streetId);
        owner.setAcquisitionSourceId(acquisitionSourceId);
        owner.setAcquisitionDate(acquisitionDate);

        boolean isUpdated = ownerDAO.updateOwner(owner);
        if (isUpdated) {
            response.sendRedirect("/owners");
        } else {
            request.setAttribute("errorMessage", "Помилка при оновленні власника.");
            doGet(request, response);
        }
    }

}
