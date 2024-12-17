package org.commercial_real_estate.automation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/send")
public class ScheduledReportController extends HttpServlet {
    private GoogleSchedulerService schedulerService;

    @Override
    public void init() {
        schedulerService = new GoogleSchedulerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/email-sender.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userEmail = request.getParameter("email");

        if (userEmail == null || userEmail.isEmpty()) {
            response.getWriter().write("Email is required!");
            return;
        }

        try {
            schedulerService.setEmail(userEmail);
            schedulerService.createWeeklyReportJob(userEmail);
            response.sendRedirect("/send?status=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/send?status=error");
        }
    }
}

