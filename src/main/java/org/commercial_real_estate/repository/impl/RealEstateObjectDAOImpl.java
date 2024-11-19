package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.RealEstateObject;
import org.commercial_real_estate.repository.RealEstateObjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RealEstateObjectDAOImpl implements RealEstateObjectDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/commercial_real_estate";
    private static final String USER = "user";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RealEstateObject> getAllRealEstateObjects() {

        List<RealEstateObject> realEstateObjects = new ArrayList<>();
        // String query = "SELECT * FROM real_estate_object";
        String query = "SELECT reo.id, s.street_name, reo.owner_id, reo.object_type_id, reo.area, reo.floor, reo.renovation, reo.furniture, reo.price, reo.building_number, reo.premise_number " +
                "FROM real_estate_object reo " +
                "JOIN street s ON reo.street_id = s.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Attempting to connect to database: " + URL);
            while (resultSet.next()) {
                RealEstateObject realEstateObject = new RealEstateObject();
                realEstateObject.setId(resultSet.getLong("id"));
                realEstateObject.setStreetName(resultSet.getString("street_name"));
               // realEstateObject.setStreetId(resultSet.getLong("street_id"));
                realEstateObject.setOwnerId(resultSet.getLong("owner_id"));
                realEstateObject.setObjectTypeId(resultSet.getLong("object_type_id"));
                realEstateObject.setArea(resultSet.getInt("area"));
                realEstateObject.setFloor(resultSet.getInt("floor"));
                realEstateObject.setRenovation(resultSet.getBoolean("renovation"));
                realEstateObject.setFurniture(resultSet.getBoolean("furniture"));
                realEstateObject.setPrice(resultSet.getInt("price"));
                realEstateObject.setBuildingNumber(resultSet.getString("building_number"));
                realEstateObject.setPremiseNumber(resultSet.getInt("premise_number"));
                realEstateObjects.add(realEstateObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realEstateObjects;
    }
}
