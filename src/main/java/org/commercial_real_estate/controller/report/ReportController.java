package org.commercial_real_estate.controller.report;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.commercial_real_estate.repository.report.ReportDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;

@Slf4j
@WebServlet("/reports")
public class ReportController extends HttpServlet {

    private ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reportType = request.getParameter("reportType");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            if ("deals".equals(reportType)) {
                Date startDate = Date.valueOf(startDateStr);
                Date endDate = Date.valueOf(endDateStr);
                File reportFile = null;
                reportDAO.generateReport(startDate, endDate);
                reportFile = new File("C:/Users/o.bulhakova/IdeaProjects/db_university_project/report/deals_report.pdf");

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"deals_report.pdf\"");

                try (FileInputStream fileInputStream = new FileInputStream(reportFile);
                     OutputStream outStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                }

            } else if ("demand".equals(reportType)) {
                Date startDate = Date.valueOf(startDateStr);
                Date endDate = Date.valueOf(endDateStr);
                File reportFile = null;
                reportDAO.generateDemandReport(startDate, endDate);
                reportFile = new File("C:/Users/o.bulhakova/IdeaProjects/db_university_project/report/demand_report.pdf");

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"demand_report.pdf\"");

                try (FileInputStream fileInputStream = new FileInputStream(reportFile);
                     OutputStream outStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                }
            }
            request.getSession().setAttribute("message", "Звіт успішно згенеровано!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
