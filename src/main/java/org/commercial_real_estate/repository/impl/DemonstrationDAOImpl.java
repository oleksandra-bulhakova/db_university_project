package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Demonstration;
import org.commercial_real_estate.model.Tenant;
import org.commercial_real_estate.repository.DemonstrationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Demonstration getDemoById(long id) {

        Demonstration demonstration = new Demonstration();
        String query = "SELECT \n" +
                "    d.id,\n" +
                "    d.date,\n" +
                "    \n" +

                "    d.demo_status_id,\n" +
                "    ds.name AS demo_status_name,\n" +
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
                "FROM demonstration d\n" +
                "LEFT JOIN demo_status ds ON d.demo_status_id = ds.id\n" +
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
                    demonstration.setId(resultSet.getLong("id"));
                    demonstration.setDate(resultSet.getDate("date"));
                    demonstration.setDemoStatus(resultSet.getString("demo_status_name"));
                    demonstration.setDemoStatusId(resultSet.getLong("demo_status_id"));
                    demonstration.setObjectAddress(resultSet.getString("street_name") + " " + resultSet.getString("object_building_number") + ", " + resultSet.getString("object_premise_number"));
                    demonstration.setObjectId(resultSet.getLong("object_id"));
                    demonstration.setTenantId(resultSet.getLong("tenant_id"));
                    demonstration.setTenantFullName(resultSet.getString("tenant_first_name") + " " + resultSet.getString("tenant_middle_name") + " " + resultSet.getString("tenant_last_name"));
                    demonstration.setRealtorId(resultSet.getLong("realtor_id"));
                    demonstration.setRealtorFullName(resultSet.getString("realtor_first_name") + " " + resultSet.getString("realtor_middle_name") + " " + resultSet.getString("realtor_last_name"));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return demonstration;
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

        String query = "SELECT id, name FROM demo_status";

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
    public void updateDemo(Demonstration demonstration) {
        String query = "UPDATE demonstration " +
                "SET date = ?, " +
                "demo_status_id = ?, " +
                "object_id = ?, " +
                "tenant_id = ?, " +
                "realtor_id = ? " +
                "WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, (Date) demonstration.getDate());
            preparedStatement.setLong(2, demonstration.getDemoStatusId());
            preparedStatement.setLong(3, demonstration.getObjectId());
            preparedStatement.setLong(4, demonstration.getTenantId());
            preparedStatement.setLong(5, demonstration.getRealtorId());
            preparedStatement.setLong(6, demonstration.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
