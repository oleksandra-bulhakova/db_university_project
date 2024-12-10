package org.commercial_real_estate.controller.stats;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.RealtorStatistics;
import org.commercial_real_estate.repository.stat.StatisticsDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/stats")
public class SourceStatsController extends HttpServlet {

    private StatisticsDAOImpl statisticsDAO = new StatisticsDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String statisticsType = request.getParameter("statisticsType");

            if ("realtor".equals(statisticsType)) {
                Date startDate = Date.valueOf(request.getParameter("startDate1"));
                Date endDate = Date.valueOf(request.getParameter("endDate1"));
                List<RealtorStatistics> realtorStatistics = statisticsDAO.getDealsStatistics(startDate, endDate);
                request.setAttribute("realtorStatistics", realtorStatistics);
                request.setAttribute("startDate1", startDate);
                request.setAttribute("endDate1", endDate);
            } else if ("source".equals(statisticsType)) {
                Date startDate = Date.valueOf(request.getParameter("startDate5"));
                Date endDate = Date.valueOf(request.getParameter("endDate5"));
                Map<String, Integer> statistics = statisticsDAO.getSourcesStatistics(startDate, endDate);
                request.setAttribute("statistics", statistics);
                request.setAttribute("startDate5", startDate);
                request.setAttribute("endDate5", endDate);
            }
            request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Переконайтеся, що дата коректна.");
            request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Виникла помилка при обробці запиту.");
        }
    }
}

