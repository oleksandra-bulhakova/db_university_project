package org.commercial_real_estate.controller.edit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Realtor;
import org.commercial_real_estate.repository.RealtorDAO;
import org.commercial_real_estate.repository.impl.RealtorDAOImpl;

import java.io.IOException;
import java.util.Map;

@WebServlet("/edit-realtor")
public class EditRealtorController extends HttpServlet {

    private RealtorDAO realtorDAO = new RealtorDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long realtorId = Long.parseLong(request.getParameter("id"));
        Realtor realtor = realtorDAO.getRealtorById(realtorId);

        if (realtor == null) {
            response.sendRedirect("/error");
            return;
        }

        Map<Long, String> streets = realtorDAO.getStreets();
        Map<Long, String> specializations = realtorDAO.getSpecializations();
        Map<Long, String> workingStatuses = realtorDAO.getWorkingStatuses();
        Map<Long, String> levels = realtorDAO.getLevels();

        request.setAttribute("realtor", realtor);
        request.setAttribute("streets", streets);
        request.setAttribute("specializations", specializations);
        request.setAttribute("workingStatuses", workingStatuses);
        request.setAttribute("levels", levels);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/edit-realtor.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        long streetId = Long.parseLong(request.getParameter("streetId"));
        String buildingNumber = request.getParameter("buildingNumber");
        int premiseNumber = Integer.parseInt(request.getParameter("premiseNumber"));
        long specializationId = Long.parseLong(request.getParameter("specializationId"));
        long workingStatusId = Long.parseLong(request.getParameter("workingStatusId"));
        long levelId = Long.parseLong(request.getParameter("levelId"));
        String startDate = request.getParameter("startDate");

        Realtor realtor = new Realtor();
        realtor.setId(id);
        realtor.setFirstName(firstName);
        realtor.setLastName(lastName);
        realtor.setMiddleName(middleName);
        realtor.setPhone(phone);
        realtor.setEmail(email);
        realtor.setStreetId(streetId);
        realtor.setBuildingNumber(buildingNumber);
        realtor.setPremiseNumber(premiseNumber);
        realtor.setSpecializationId(specializationId);
        realtor.setWorkingStatusId(workingStatusId);
        realtor.setLevelId(levelId);
        realtor.setStartDate(java.sql.Date.valueOf(startDate));

        realtorDAO.updateRealtor(realtor);

        response.sendRedirect("/realtors");
    }
}
