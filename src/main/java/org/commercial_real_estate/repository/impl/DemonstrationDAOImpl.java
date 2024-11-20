package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Demonstration;
import org.commercial_real_estate.repository.DemonstrationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemonstrationDAOImpl implements DemonstrationDAO {

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
    public List<Demonstration> getAllDemonstrations() {
        List<Demonstration> demonstrations = new ArrayList<>();
        String query = "SELECT d.id, d.date, ds.name AS demo_status, " +
                "CONCAT(s.street_name, ', ', o.building_number, ', прим. ', o.premise_number) AS object_address, " +
                "CONCAT(t.first_name, ' ', t.middle_name, ' ', t.last_name) AS tenant_full_name, " +
                "CONCAT(r.first_name, ' ', r.middle_name, ' ', r.last_name) AS realtor_full_name " +
                "FROM demonstration d " +
                "JOIN demo_status ds ON d.demo_status_id = ds.id " +
                "JOIN real_estate_object o ON d.object_id = o.id " +
                "JOIN street s ON o.street_id = s.id " +
                "JOIN tenant t ON d.tenant_id = t.id " +
                "JOIN realtor r ON d.realtor_id = r.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Demonstration demonstration = new Demonstration();
                demonstration.setId(resultSet.getLong("id"));
                demonstration.setDate(resultSet.getDate("date"));
                demonstration.setDemoStatus(resultSet.getString("demo_status"));
                demonstration.setObjectAddress(resultSet.getString("object_address"));
                demonstration.setTenantFullName(resultSet.getString("tenant_full_name"));
                demonstration.setRealtorFullName(resultSet.getString("realtor_full_name"));
                demonstrations.add(demonstration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demonstrations;
    }
}
