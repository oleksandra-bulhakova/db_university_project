package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.entities.Deal;
import org.commercial_real_estate.repository.DealDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Deal getDealById(long id) {
        Deal deal = new Deal();
        String query = "SELECT \n" +
                "    d.id,\n" +
                "    d.date,\n" +
                "    d.price, \n" +
                "    \n" +

                "    d.deal_status_id,\n" +
                "    ds.name AS deal_status_name,\n" +
                "    \n" +

                "    d.object_id,\n" +
                "    ro.street_id AS object_street_id,\n" +
                "    s.street_name,\n" +
                "    ro.building_number AS object_building_number,\n" +
                "    ro.premise_number AS object_premise_number,\n" +
                "    \n" +

                "    d.tenant_id,\n" +
                "    t.first_name AS tenant_first_name,\n" +
                "    t.last_name AS tenant_last_name,\n" +
                "    t.middle_name AS tenant_middle_name,\n" +
                "    t.phone AS tenant_phone,\n" +
                "    t.email AS tenant_email,\n" +
                "    \n" +

                "    d.realtor_id,\n" +
                "    r.first_name AS realtor_first_name,\n" +
                "    r.last_name AS realtor_last_name,\n" +
                "    r.middle_name AS realtor_middle_name,\n" +
                "    r.phone AS realtor_phone,\n" +
                "    r.email AS realtor_email\n" +
                "    \n" +
                "FROM deal d\n" +
                "LEFT JOIN deal_status ds ON d.deal_status_id = ds.id\n" +
                "LEFT JOIN real_estate_object ro ON d.object_id = ro.id\n" +
                "LEFT JOIN street s ON ro.street_id = s.id\n" +
                "LEFT JOIN tenant t ON d.tenant_id = t.id\n" +
                "LEFT JOIN realtor r ON d.realtor_id = r.id\n" +
                "WHERE d.id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    deal.setId(resultSet.getLong("id"));
                    deal.setDate(resultSet.getDate("date"));
                    deal.setObjectId(resultSet.getLong("object_id"));
                    deal.setObjectAddress(resultSet.getString("street_name") + " " + resultSet.getString("object_building_number") + ", " + resultSet.getString("object_premise_number"));
                    deal.setTenantFullName(resultSet.getString("tenant_first_name") + " " + resultSet.getString("tenant_middle_name") + " " + resultSet.getString("tenant_last_name"));
                    deal.setTenantId(resultSet.getLong("tenant_id"));
                    deal.setRealtorFullName(resultSet.getString("realtor_first_name") + " " + resultSet.getString("realtor_middle_name") + " " + resultSet.getString("realtor_last_name"));
                    deal.setRealtorId(resultSet.getLong("realtor_id"));
                    deal.setDealStatus(resultSet.getString("deal_status_name"));
                    deal.setDealStatusId(resultSet.getLong("deal_status_id"));
                    deal.setPrice(resultSet.getInt("price"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deal;
    }

    @Override
    public Map<Long, String> getObjects() {

        Map<Long, String> objects = new HashMap<Long, String>();

        String query = "SELECT " +
                "ro.id, " +
                "ro.building_number, " +
                "ro.premise_number, " +
                "s.street_name " +
                "FROM real_estate_object ro " +
                "LEFT JOIN street s ON ro.street_id = s.id;";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                objects.put(resultSet.getLong("id"), (resultSet.getString("street_name") + " " +
                        resultSet.getString("building_number") + ", прим. " + resultSet.getString("premise_number")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Map<Long, String> getStatuses() {
        Map<Long, String> statuses = new HashMap<Long, String>();

        String query = "SELECT id, name FROM deal_status";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                statuses.put(resultSet.getLong("id"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statuses;
    }

    @Override
    public Map<Long, String> getTenants() {
        Map<Long, String> tenants = new HashMap<Long, String>();

        String query = "SELECT " +
                "id, " +
                "first_name, " +
                "middle_name, " +
                "last_name " +
                "FROM tenant;";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tenants.put(resultSet.getLong("id"), (resultSet.getString("first_name") + " " +
                        resultSet.getString("middle_name") + " " + resultSet.getString("last_name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tenants;
    }

    @Override
    public Map<Long, String> getRealtors() {
        Map<Long, String> realtors = new HashMap<Long, String>();

        String query = "SELECT " +
                "id, " +
                "first_name, " +
                "middle_name, " +
                "last_name " +
                "FROM realtor;";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                realtors.put(resultSet.getLong("id"), (resultSet.getString("first_name") + " " +
                        resultSet.getString("middle_name") + " " + resultSet.getString("last_name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return realtors;
    }

    @Override
    public void updateDeal(Deal deal) {
        String query = "UPDATE deal " +
                "SET date = ?, " +
                "deal_status_id = ?, " +
                "object_id = ?, " +
                "tenant_id = ?, " +
                "realtor_id = ?, " +
                "price = ? " +
                "WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, (Date) deal.getDate());
            preparedStatement.setLong(2, deal.getDealStatusId());
            preparedStatement.setLong(3, deal.getObjectId());
            preparedStatement.setLong(4, deal.getTenantId());
            preparedStatement.setLong(5, deal.getRealtorId());
            preparedStatement.setInt(6, deal.getPrice());
            preparedStatement.setLong(7, deal.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createDeal(Deal deal) {
        String query = "INSERT INTO deal (date, deal_status_id, object_id, tenant_id, realtor_id, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, (Date) deal.getDate());
            preparedStatement.setLong(2, deal.getDealStatusId());
            preparedStatement.setLong(3, deal.getObjectId());
            preparedStatement.setLong(4, deal.getTenantId());
            preparedStatement.setLong(5, deal.getRealtorId());
            preparedStatement.setInt(6, deal.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDeal(long id) {
        String query = "DELETE FROM deal WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
