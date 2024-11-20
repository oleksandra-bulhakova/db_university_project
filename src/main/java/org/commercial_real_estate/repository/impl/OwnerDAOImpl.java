package org.commercial_real_estate.repository.impl;

import lombok.Data;
import org.commercial_real_estate.model.Owner;
import org.commercial_real_estate.repository.OwnerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

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
    public List<Owner> getAllOwners() {

        List<Owner> owners = new ArrayList<>();
        String query = "SELECT o.id, o.first_name, o.last_name, o.middle_name, o.phone, o.email, " +
                "o.acquisition_date, o.building_number, o.premise_number, s.street_name, " +
                "asource.source_name " +
                "FROM owner o " +
                "JOIN street s ON o.street_id = s.id " +
                "JOIN acquisition_source asource ON o.acquisition_source_id = asource.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Owner owner = new Owner();
                owner.setId(resultSet.getLong("id"));
                owner.setFirstName(resultSet.getString("first_name"));
                owner.setLastName(resultSet.getString("last_name"));
                owner.setMiddleName(resultSet.getString("middle_name"));
                owner.setPhone(resultSet.getString("phone"));
                owner.setEmail(resultSet.getString("email"));
                owner.setAcquisitionDate(resultSet.getDate("acquisition_date"));
                owner.setBuildingNumber(resultSet.getString("building_number"));
                owner.setPremiseNumber(resultSet.getInt("premise_number"));
                owner.setStreetName(resultSet.getString("street_name"));
                owner.setAcquisitionSourceName(resultSet.getString("source_name"));
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }

    public boolean updateOwner(Owner owner) {
        String sql = "UPDATE owner SET street_id = ?, acquisition_source_id = ?, first_name = ?, last_name = ?, " +
                "middle_name = ?, phone = ?, email = ?, acquisition_date = ?, building_number = ?, " +
                "premise_number = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, owner.getStreetId());
            ps.setLong(2, owner.getAcquisitionSourceId());
            ps.setString(3, owner.getFirstName());
            ps.setString(4, owner.getLastName());
            ps.setString(5, owner.getMiddleName());
            ps.setString(6, owner.getPhone());
            ps.setString(7, owner.getEmail());
            ps.setDate(8, new java.sql.Date(owner.getAcquisitionDate().getTime()));
            ps.setString(9, owner.getBuildingNumber());
            ps.setInt(10, owner.getPremiseNumber());
            ps.setLong(11, owner.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}