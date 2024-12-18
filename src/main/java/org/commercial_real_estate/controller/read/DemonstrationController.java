package org.commercial_real_estate.controller.read;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.Demonstration;
import org.commercial_real_estate.repository.DemonstrationDAO;
import org.commercial_real_estate.repository.impl.DemonstrationDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/demonstrations")
public class DemonstrationController extends HttpServlet {

    private DemonstrationDAO demonstrationDAO = new DemonstrationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterType = request.getParameter("filterType");
        List<Demonstration> demonstrations;
        if (filterType != null && !filterType.isEmpty()) {
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            demonstrations = demonstrationDAO.filterByDate(startDate, endDate);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
        } else {
            demonstrations = demonstrationDAO.getAllDemonstrations();
        }

        request.setAttribute("demonstrations", demonstrations);
        request.getRequestDispatcher("/WEB-INF/views/demonstrations.jsp").forward(request, response);
    }
}
