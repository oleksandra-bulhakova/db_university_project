package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.Tenant;
import org.commercial_real_estate.repository.TenantDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                "t.acquisition_date, t.building_number, t.premise_number, t.desired_area, t.budget, t.desired_district_id," +
                "t.desired_object_type_id, st.street_name, " +
                "acq.source_name, d.district_name, o.object_type_name " +
                "FROM tenant t " +
                "JOIN street st ON t.street_id = st.id " +
                "JOIN district d ON t.desired_district_id = d.id " +
                "JOIN object_type o ON t.desired_object_type_id = o.id " +
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
                tenant.setDesiredObjectTypeId(resultSet.getLong("desired_object_type_id"));
                tenant.setDesiredObjectTypeName(resultSet.getString("object_type_name"));
                tenant.setDesiredDistrictId(resultSet.getLong("desired_district_id"));
                tenant.setDesiredDistrictName(resultSet.getString("district_name"));
                tenant.setDesiredArea(resultSet.getInt("desired_area"));
                tenant.setBudget(resultSet.getInt("budget"));
                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    @Override
    public Tenant getTenantById(long id) {

        Tenant tenant = new Tenant();
        String query = "SELECT \n" +
                "    t.id,\n" +
                "    t.first_name,\n" +
                "    t.last_name,\n" +
                "    t.middle_name,\n" +
                "    t.phone,\n" +
                "    t.email,\n" +
                "    t.acquisition_date,\n" +
                "    t.building_number,\n" +
                "    t.premise_number,\n" +
                "    t.street_id,\n" +
                "    s.street_name,\n" +
                "    t.desired_district_id, \n" +
                "t.acquisition_source_id,\n" +
                "t.desired_object_type_id, \n" +
                "    d.district_name AS desired_district_name,\n" +
                "    ot.object_type_name AS desired_object_type_name,\n" +
                "    ac.source_name AS acquisition_source_name,\n" +
                "    t.budget,\n" +
                "    t.desired_area\n" +
                "FROM \n" +
                "    tenant t\n" +
                "LEFT JOIN street s ON t.street_id = s.id\n" +
                "LEFT JOIN district d ON t.desired_district_id = d.id\n" +
                "LEFT JOIN object_type ot ON t.desired_object_type_id = ot.id\n" +
                "LEFT JOIN acquisition_source ac ON t.acquisition_source_id = ac.id\n" +
                "WHERE \n" +
                "    t.id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
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
                    tenant.setAcquisitionSourceName(resultSet.getString("acquisition_source_name"));
                    tenant.setBudget(resultSet.getInt("budget"));
                    tenant.setDesiredArea(resultSet.getInt("desired_area"));
                    tenant.setDesiredDistrictName(resultSet.getString("desired_district_name"));
                    tenant.setDesiredObjectTypeName(resultSet.getString("desired_object_type_name"));
                    tenant.setStreetId(resultSet.getLong("street_id"));
                    tenant.setDesiredDistrictId(resultSet.getLong("desired_district_id"));
                    tenant.setDesiredObjectTypeId(resultSet.getLong("desired_object_type_id"));
                    tenant.setAcquisitionSourceId(resultSet.getLong("acquisition_source_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tenant;
    }

    @Override
    public void updateTenant(Tenant tenant) {

        String query = "UPDATE tenant \n" +
                "SET \n" +
                "    street_id = ?, \n" +
                "    acquisition_source_id = ?, \n" +
                "    first_name = ?, \n" +
                "    last_name = ?, \n" +
                "    middle_name = ?, \n" +
                "    phone = ?, \n" +
                "    email = ?, \n" +
                "    acquisition_date = ?, \n" +
                "    building_number = ?, \n" +
                "    premise_number = ?, \n" +
                "    desired_object_type_id = ?, \n" +
                "    desired_district_id = ?, \n" +
                "    budget = ?, \n" +
                "    desired_area = ?\n" +
                "WHERE \n" +
                "    id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, tenant.getStreetId());
            preparedStatement.setLong(2, tenant.getAcquisitionSourceId());
            preparedStatement.setString(3, tenant.getFirstName());
            preparedStatement.setString(4, tenant.getLastName());
            preparedStatement.setString(5, tenant.getMiddleName());
            preparedStatement.setString(6, tenant.getPhone());
            preparedStatement.setString(7, tenant.getEmail());
            preparedStatement.setDate(8, (Date) tenant.getAcquisitionDate());
            preparedStatement.setString(9, tenant.getBuildingNumber());
            preparedStatement.setInt(10, tenant.getPremiseNumber());
            preparedStatement.setLong(11, tenant.getDesiredObjectTypeId());
            preparedStatement.setLong(12, tenant.getDesiredDistrictId());
            preparedStatement.setLong(13, tenant.getBudget());
            preparedStatement.setLong(14, tenant.getDesiredArea());
            preparedStatement.setLong(15, tenant.getId());

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
    public Map<Long, String> getAllAcquisitionSources() {

        Map<Long, String> sources = new HashMap<Long, String>();

        String query = "SELECT id, source_name FROM acquisition_source";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                sources.put(resultSet.getLong("id"), resultSet.getString("source_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sources;
    }

    @Override
    public Map<Long, String> getDistricts() {

        Map<Long, String> districts = new HashMap<Long, String>();

        String query = "SELECT id, district_name FROM district";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                districts.put(resultSet.getLong("id"), resultSet.getString("district_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return districts;
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
    public void createTenant(Tenant tenant) {
        String email = tenant.getEmail();
        List<Tenant> tenants = getAllTenants();
        if (tenants.stream()
                .anyMatch(t -> t.getEmail().equals(email))) {
            throw new IllegalArgumentException("Tenant with this email already exists: " + email);
        }

        String query = "INSERT INTO tenant (street_id, acquisition_source_id, first_name, last_name, middle_name, " +
                "phone, email, acquisition_date, building_number, premise_number, desired_object_type_id, desired_district_id, " +
                "budget, desired_area) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, tenant.getStreetId());
            preparedStatement.setLong(2, tenant.getAcquisitionSourceId());
            preparedStatement.setString(3, tenant.getFirstName());
            preparedStatement.setString(4, tenant.getLastName());
            preparedStatement.setString(5, tenant.getMiddleName());
            preparedStatement.setString(6, tenant.getPhone());
            preparedStatement.setString(7, tenant.getEmail());
            preparedStatement.setDate(8, (Date) tenant.getAcquisitionDate());
            preparedStatement.setString(9, tenant.getBuildingNumber());
            preparedStatement.setInt(10, tenant.getPremiseNumber());
            preparedStatement.setLong(11, tenant.getDesiredObjectTypeId());
            preparedStatement.setLong(12, tenant.getDesiredDistrictId());
            preparedStatement.setLong(13, tenant.getBudget());
            preparedStatement.setLong(14, tenant.getDesiredArea());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTenant(long id) throws SQLIntegrityConstraintViolationException {
        String query = "DELETE FROM tenant WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw new SQLIntegrityConstraintViolationException("Cannot delete tenant: foreign key constraint violation", e);
            }
            else {
                e.printStackTrace();
            }
        }
    }
}
