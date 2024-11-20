package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Tenant;
import org.commercial_real_estate.repository.TenantDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenantDAOImpl implements TenantDAO {

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
    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        String query = "SELECT t.id, t.first_name, t.last_name, t.middle_name, t.phone, t.email, " +
                "t.acquisition_date, t.building_number, t.premise_number, st.street_name, " +
                "acq.source_name " +
                "FROM tenant t " +
                "JOIN street st ON t.street_id = st.id " +
                "JOIN acquisition_source acq ON t.acquisition_source_id = acq.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Tenant tenant = new Tenant();
                tenant.setId(resultSet.getLong("id"));
                tenant.setFirstName(resultSet.getString("first_name"));
                tenant.setLastName(resultSet.getString("last_name"));
                tenant.setMiddleName(resultSet.getString("middle_name"));
                tenant.setPhone(resultSet.getString("phone"));
                tenant.setEmail(resultSet.getString("email"));
                tenant.setAcquisitionDate(resultSet.getDate("acquisition_date"));
                tenant.setBuildingNumber(resultSet.getString("building_number"));
                tenant.setPremiseNumber(resultSet.getInt("premise_number"));
                tenant.setStreetName(resultSet.getString("street_name"));
                tenant.setAcquisitionSourceName(resultSet.getString("source_name"));
                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }
}
