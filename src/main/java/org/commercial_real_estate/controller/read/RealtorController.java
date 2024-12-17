package org.commercial_real_estate.controller.read;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/realtors")
public class RealtorController extends HttpServlet {

    private RealtorDAO realtorDAO = new RealtorDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortDirection = request.getParameter("sortDirection");
        String searchQuery = request.getParameter("search");
        String specialization = request.getParameter("specialization");

        List<Realtor> realtors = new ArrayList<>();

        if (searchQuery != null && !searchQuery.isEmpty()) {
            try {
                realtors = realtorDAO.searchRealtors(searchQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            realtors = realtorDAO.getAllRealtors();
        }

        if (specialization != null && !specialization.isEmpty()) {
            realtors = realtors.stream()
                    .filter(realtor -> realtor.getSpecialization().equalsIgnoreCase(specialization))
                    .collect(Collectors.toList());
        }

        if (sortDirection == null || sortDirection.equals("asc")) {
            realtors.sort((r1, r2) -> {
                int result = r1.getLastName().compareToIgnoreCase(r2.getLastName());
                if (result != 0) return result;
                result = r1.getFirstName().compareToIgnoreCase(r2.getFirstName());
                if (result != 0) return result;
                return r1.getMiddleName().compareToIgnoreCase(r2.getMiddleName());
            });
        } else {
            realtors.sort((r1, r2) -> {
                int result = r2.getLastName().compareToIgnoreCase(r1.getLastName());
                if (result != 0) return result;
                result = r2.getFirstName().compareToIgnoreCase(r1.getFirstName());
                if (result != 0) return result;
                return r2.getMiddleName().compareToIgnoreCase(r1.getMiddleName());
            });
        }

        request.setAttribute("realtors", realtors);
        request.setAttribute("sortDirection", sortDirection);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("specialization", specialization);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/realtors.jsp");
        dispatcher.forward(request, response);
    }
}
