package org.commercial_real_estate.automation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/generate-report")
public class GenerateReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmailService emailService = new EmailService();

    private AutoReportDAO autoReportDAO;

    @Override
    public void init() throws ServletException {
        this.autoReportDAO = new AutoReportDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userEmail = request.getParameter("email");
        try {
            autoReportDAO.generateAutoReport();
            GCSFileDownloader.downloadFile();
            emailService.sendEmail(userEmail, "Ваш щотижневий звіт", "Ваш щотижневий звіт у прикріпленому файлі", "C:/Users/o.bulhakova/IdeaProjects/db_university_project/report/weekly.pdf");

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/send?status=success");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("/send?status=error");
        }
    }
}
