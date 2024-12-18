package org.commercial_real_estate.sql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/execute-query")
public class ExecuteQueryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QueryDAO queryDAO;

    @Override
    public void init() {
        this.queryDAO = new QueryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/sql.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sqlQuery = request.getParameter("query");
        List<List<String>> result = null;
        try {
            result = queryDAO.executeQuery(sqlQuery);

            request.setAttribute("result", result);
            request.getRequestDispatcher("/WEB-INF/views/sql.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Помилка виконання запиту: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/sql.jsp").forward(request, response);
        }
    }
}