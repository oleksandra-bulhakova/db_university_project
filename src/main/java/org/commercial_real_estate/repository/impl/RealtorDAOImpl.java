package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Realtor;
import org.commercial_real_estate.repository.RealtorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RealtorDAOImpl implements RealtorDAO {

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
    public List<Realtor> getAllRealtors() {
        List<Realtor> realtors = new ArrayList<>();
        String query = "SELECT r.id, CONCAT(r.first_name,' ', r.middle_name, ' ', r.last_name) AS full_name, " +
                "CONCAT(s.street_name, ', ', r.building_number, ', кв. ', r.premise_number) AS address, " +
                "ls.level_name AS level, ss.specialization_name AS specialization, ws.status_name AS working_status, r.start_date " +
                "FROM realtor r " +
                "JOIN street s ON r.street_id = s.id " +
                "JOIN level ls ON r.level_id = ls.id " +
                "JOIN specialization ss ON r.specialization_id = ss.id " +
                "JOIN working_status ws ON r.working_status_id = ws.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Realtor realtor = new Realtor();
                realtor.setId(resultSet.getLong("id"));
                realtor.setFullName(resultSet.getString("full_name"));
                realtor.setAddress(resultSet.getString("address"));
                realtor.setLevel(resultSet.getString("level"));
                realtor.setSpecialization(resultSet.getString("specialization"));
                realtor.setWorkingStatus(resultSet.getString("working_status"));
                realtor.setStartDate(resultSet.getDate("start_date"));
                realtors.add(realtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realtors;
    }
}
