package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.Owner;
import org.commercial_real_estate.repository.OwnerDAO;
import org.commercial_real_estate.repository.impl.OwnerDAOImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/owners")
public class OwnerController extends HttpServlet {

    private OwnerDAO ownerDAO = new OwnerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Owner> owners = ownerDAO.getAllOwners();
        request.setAttribute("owners", owners);
        request.getRequestDispatcher("/WEB-INF/views/owners.jsp").forward(request, response);
    }
}
