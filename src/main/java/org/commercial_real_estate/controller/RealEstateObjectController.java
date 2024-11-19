package org.commercial_real_estate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.RealEstateObject;
import org.commercial_real_estate.repository.RealEstateObjectDAO;
import org.commercial_real_estate.repository.impl.RealEstateObjectDAOImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/real-estate-objects")
public class RealEstateObjectController extends HttpServlet {

    private RealEstateObjectDAO realEstateObjectDAO;

    @Override
    public void init() {
        realEstateObjectDAO = new RealEstateObjectDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RealEstateObject> realEstateObjects = realEstateObjectDAO.getAllRealEstateObjects();
        req.setAttribute("realEstateObjects", realEstateObjects);
        req.getRequestDispatcher("/WEB-INF/views/real-estate-objects.jsp").forward(req, resp);
    }
}
