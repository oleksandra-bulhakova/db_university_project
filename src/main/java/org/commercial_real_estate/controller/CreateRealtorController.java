package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Realtor;
import org.commercial_real_estate.repository.RealtorDAO;
import org.commercial_real_estate.repository.impl.RealtorDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/realtors/create-realtor")
public class CreateRealtorController extends HttpServlet {

    RealtorDAO realtorDAO = new RealtorDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Realtor realtor = new Realtor();
        request.setAttribute("realtor", realtor);

        Map<Long, String> streets = realtorDAO.getStreets();
        Map<Long, String> specializations = realtorDAO.getSpecializations();
        Map<Long, String> workingStatuses = realtorDAO.getWorkingStatuses();
        Map<Long, String> levels = realtorDAO.getLevels();

        request.setAttribute("streets", streets);
        request.setAttribute("specializations", specializations);
        request.setAttribute("workingStatuses", workingStatuses);
        request.setAttribute("levels", levels);

        request.getRequestDispatcher("/WEB-INF/views/create-realtor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Realtor realtor = new Realtor();
        realtor.setStreetId(Long.parseLong(request.getParameter("streetId")));
        realtor.setBuildingNumber(request.getParameter("buildingNumber"));
        realtor.setPremiseNumber(Integer.parseInt(request.getParameter("premiseNumber")));
        realtor.setWorkingStatusId(Integer.parseInt(request.getParameter("workingStatusId")));
        realtor.setLevelId(Integer.parseInt(request.getParameter("levelId")));
        realtor.setSpecializationId(Integer.parseInt(request.getParameter("specializationId")));
        realtor.setFirstName(request.getParameter("firstName"));
        realtor.setLastName(request.getParameter("lastName"));
        realtor.setMiddleName(request.getParameter("middleName"));
        realtor.setEmail(request.getParameter("email"));
        realtor.setPhone(request.getParameter("phone"));
        realtor.setStartDate(Date.valueOf(request.getParameter("startDate")));

        realtorDAO.createRealtor(realtor);
        response.sendRedirect("/realtors");
    }
}
