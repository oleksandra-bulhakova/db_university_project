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

        String query = "SELECT reo.id, " +
                "CONCAT(s.street_name, ', ', reo.building_number, ', прим. ', reo.premise_number) AS address, " +
                "o.first_name, o.middle_name, o.last_name, ot.object_type_name, reo.area, reo.floor, " +
                "reo.renovation, reo.furniture, reo.price " +
                "FROM real_estate_object reo " +
                "JOIN street s ON reo.street_id = s.id " +
                "JOIN owner o ON reo.owner_id = o.id " +
                "JOIN object_type ot ON reo.object_type_id = ot.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                RealEstateObject realEstateObject = new RealEstateObject();
                realEstateObject.setId(resultSet.getLong("id"));
                realEstateObject.setAddress(resultSet.getString("address"));
                realEstateObject.setOwnerFullName(resultSet.getString("first_name") + " "  + resultSet.getString("middle_name") + " " + resultSet.getString("last_name"));
                realEstateObject.setObjectType(resultSet.getString("object_type_name"));
                realEstateObject.setArea(resultSet.getInt("area"));
                realEstateObject.setFloor(resultSet.getInt("floor"));
                realEstateObject.setRenovation(resultSet.getBoolean("renovation"));
                realEstateObject.setFurniture(resultSet.getBoolean("furniture"));
                realEstateObject.setPrice(resultSet.getInt("price"));
                realEstateObjects.add(realEstateObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realEstateObjects;
    }
}
