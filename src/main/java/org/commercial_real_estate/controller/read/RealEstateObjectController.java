package org.commercial_real_estate.controller.read;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.commercial_real_estate.model.entities.RealEstateObject;
import org.commercial_real_estate.repository.RealEstateObjectDAO;
import org.commercial_real_estate.repository.impl.RealEstateObjectDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
        String searchQuery = req.getParameter("search");
        String sortDirection = req.getParameter("sortDirection");
        List<RealEstateObject> realEstateObjects;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            realEstateObjects = realEstateObjectDAO.searchObjects(searchQuery);
        } else {
            realEstateObjects = realEstateObjectDAO.getAllRealEstateObjects();
        }

        Comparator<RealEstateObject> comparator = Comparator.comparingInt(RealEstateObject::getPrice);
        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }
        realEstateObjects.sort(comparator);

        req.setAttribute("searchQuery", searchQuery);
        req.setAttribute("realEstateObjects", realEstateObjects);
        req.setAttribute("sortDirection", sortDirection);
        req.getRequestDispatcher("/WEB-INF/views/real-estate-objects.jsp").forward(req, resp);
    }
}

