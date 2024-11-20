package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Deal;
import org.commercial_real_estate.repository.DealDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealDAOImpl implements DealDAO {

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
    public List<Deal> getAllDeals() {
        List<Deal> deals = new ArrayList<>();
//        String query = "SELECT d.id, d.date, ds.name AS deal_status, " +
//                "CONCAT(o.building_number, 'прим. ', o.premise_number) AS object_address, " +
//                "CONCAT(t.first_name, ' ', t.middle_name, ' ', t.last_name) AS tenant_full_name, " +
//                "CONCAT(r.first_name, ' ', r.middle_name, ' ', r.last_name) AS realtor_full_name, " +
//                "d.price " +
//                "FROM deal d " +
//                "JOIN deal_status ds ON d.deal_status_id = ds.id " +
//                "JOIN real_estate_object o ON d.object_id = o.id " +
//                "JOIN tenant t ON d.tenant_id = t.id " +
//                "JOIN realtor r ON d.realtor_id = r.id";

        String query = "SELECT d.id, d.date, ds.name AS deal_status, " +
                "CONCAT(s.street_name, ', ', o.building_number, ', прим. ', o.premise_number) AS object_address, " +
                "CONCAT(t.first_name, ' ', t.middle_name, ' ', t.last_name) AS tenant_full_name, " +
                "CONCAT(r.first_name, ' ', r.middle_name, ' ', r.last_name) AS realtor_full_name, " +
                "d.price " +
                "FROM deal d " +
                "JOIN deal_status ds ON d.deal_status_id = ds.id " +
                "JOIN real_estate_object o ON d.object_id = o.id " +
                "JOIN street s ON o.street_id = s.id " +
                "JOIN tenant t ON d.tenant_id = t.id " +
                "JOIN realtor r ON d.realtor_id = r.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Deal deal = new Deal();
                deal.setId(resultSet.getLong("id"));
                deal.setDate(resultSet.getDate("date"));
                deal.setDealStatus(resultSet.getString("deal_status"));
                deal.setObjectAddress(resultSet.getString("object_address"));
                deal.setTenantFullName(resultSet.getString("tenant_full_name"));
                deal.setRealtorFullName(resultSet.getString("realtor_full_name"));
                deal.setPrice(resultSet.getInt("price"));
                deals.add(deal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deals;
    }
}
