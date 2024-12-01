package org.commercial_real_estate.repository.impl;

import com.mysql.cj.jdbc.ConnectionWrapper;
import org.commercial_real_estate.model.RealEstateObject;
import org.commercial_real_estate.model.Tenant;
import org.commercial_real_estate.repository.RealEstateObjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                realEstateObject.setOwnerFullName(resultSet.getString("first_name") + " " + resultSet.getString("middle_name") + " " + resultSet.getString("last_name"));
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

    @Override
    public RealEstateObject getRealEstateObjectById(long id) {

        RealEstateObject realEstateObject = new RealEstateObject();
        String query = "SELECT \n" +
                "    reo.id,\n" +
                "    reo.building_number,\n" +
                "    reo.premise_number AS object_premise_number,\n" +
                "    reo.area AS object_area,\n" +
                "    reo.floor AS object_floor,\n" +
                "    reo.renovation AS object_renovation,\n" +
                "    reo.furniture AS object_furniture,\n" +
                "    reo.price AS object_price,\n" +
                "   reo.owner_id,\n" +
                "    st.street_name AS street_name,\n" +
                "   st.id AS street_id,\n" +
                "    ot.object_type_name AS object_type_name,\n" +
                "    ot.id AS object_type_id,\n" +
                "    CONCAT(o.first_name, ' ', o.last_name, ' ', o.middle_name) AS owner_full_name\n" +
                "FROM \n" +
                "    real_estate_object reo\n" +
                "JOIN \n" +
                "    street st ON reo.street_id = st.id\n" +
                "JOIN \n" +
                "    object_type ot ON reo.object_type_id = ot.id\n" +
                "JOIN \n" +
                "    owner o ON reo.owner_id = o.id\n" +
                "WHERE \n" +
                "    reo.id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    realEstateObject.setId(resultSet.getLong("id"));
                    realEstateObject.setStreetName(resultSet.getString("street_name"));
                    realEstateObject.setObjectType(resultSet.getString("object_type_name"));
                    realEstateObject.setOwnerFullName(resultSet.getString("owner_full_name"));
                    realEstateObject.setArea(resultSet.getInt("object_area"));
                    realEstateObject.setFloor(resultSet.getInt("object_floor"));
                    realEstateObject.setRenovation(resultSet.getBoolean("object_renovation"));
                    realEstateObject.setFurniture(resultSet.getBoolean("object_furniture"));
                    realEstateObject.setPrice(resultSet.getInt("object_price"));
                    realEstateObject.setBuildingNumber(resultSet.getString("building_number"));
                    realEstateObject.setPremiseNumber(resultSet.getInt("object_premise_number"));
                    realEstateObject.setObjectTypeId(resultSet.getInt("object_type_id"));
                    realEstateObject.setStreetId(resultSet.getInt("street_id"));
                    realEstateObject.setOwnerId(resultSet.getInt("owner_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return realEstateObject;
    }

    @Override
    public void updateObject(RealEstateObject realEstateObject) {

        String query = "UPDATE real_estate_object\n" +
                "SET \n" +
                "    street_id = ?,\n" +
                "    owner_id = ?,\n" +
                "    object_type_id = ?,\n" +
                "    area = ?,\n" +
                "    floor = ?,\n" +
                "    renovation = ?,\n" +
                "    furniture = ?,\n" +
                "    price = ?,\n" +
                "    building_number = ?,\n" +
                "    premise_number = ?\n" +
                "WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, realEstateObject.getStreetId());
            preparedStatement.setLong(2, realEstateObject.getOwnerId());
            preparedStatement.setLong(3, realEstateObject.getObjectTypeId());
            preparedStatement.setInt(4, realEstateObject.getArea());
            preparedStatement.setInt(5, realEstateObject.getFloor());
            preparedStatement.setBoolean(6, realEstateObject.isRenovation());
            preparedStatement.setBoolean(7, realEstateObject.isFurniture());
            preparedStatement.setInt(8, realEstateObject.getPrice());
            preparedStatement.setString(9, realEstateObject.getBuildingNumber());
            preparedStatement.setInt(10, realEstateObject.getPremiseNumber());
            preparedStatement.setLong(11, realEstateObject.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, String> getStreets() {

        Map<Long, String> streets = new HashMap<Long, String>();

        String query = "SELECT id, street_name FROM street";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                streets.put(resultSet.getLong("id"), resultSet.getString("street_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return streets;
    }

    @Override
    public Map<Long, String> getObjectTypes() {

        Map<Long, String> object_type = new HashMap<Long, String>();

        String query = "SELECT id, object_type_name FROM object_type";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                object_type.put(resultSet.getLong("id"), resultSet.getString("object_type_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return object_type;
    }

    @Override
    public Map<Long, String> getOwners() {

        Map<Long, String> owners = new HashMap<Long, String>();

        String query = "SELECT id, first_name, middle_name, last_name FROM owner";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("first_name") + " " + resultSet.getString("middle_name") + " " + resultSet.getString("last_name");
                owners.put(resultSet.getLong("id"), name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return owners;
    }

    @Override
    public void createObject(RealEstateObject realEstateObject) {
        String query = "INSERT INTO real_estate_object (street_id, owner_id, object_type_id, area, floor, renovation, furniture, price, building_number, premise_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, realEstateObject.getStreetId());
            preparedStatement.setLong(2, realEstateObject.getOwnerId());
            preparedStatement.setLong(3, realEstateObject.getObjectTypeId());
            preparedStatement.setInt(4, realEstateObject.getArea());
            preparedStatement.setInt(5, realEstateObject.getFloor());
            preparedStatement.setBoolean(6, realEstateObject.isRenovation());
            preparedStatement.setBoolean(7, realEstateObject.isFurniture());
            preparedStatement.setInt(8, realEstateObject.getPrice());
            preparedStatement.setString(9, realEstateObject.getBuildingNumber());
            preparedStatement.setInt(10, realEstateObject.getPremiseNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteObjectByd(long id) throws SQLException {
        String query = "DELETE FROM real_estate_object WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw new SQLIntegrityConstraintViolationException("Cannot delete object: foreign key constraint violation", e);
            } else {
                throw e;
            }
        }
    }
}
