package org.commercial_real_estate.repository.impl;

import org.commercial_real_estate.model.entities.Realtor;
import org.commercial_real_estate.repository.RealtorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

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
        String query = "SELECT r.id, r.email, r.phone, r.first_name, " +
                "r.last_name, r.middle_name, CONCAT(r.first_name,' ', r.middle_name, ' ', r.last_name) AS full_name, " +
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
                realtor.setFirstName(resultSet.getString("first_name"));
                realtor.setMiddleName(resultSet.getString("middle_name"));
                realtor.setLastName(resultSet.getString("last_name"));
                realtor.setAddress(resultSet.getString("address"));
                realtor.setLevel(resultSet.getString("level"));
                realtor.setSpecialization(resultSet.getString("specialization"));
                realtor.setWorkingStatus(resultSet.getString("working_status"));
                realtor.setStartDate(resultSet.getDate("start_date"));
                realtor.setEmail(resultSet.getString("email"));
                realtor.setPhone(resultSet.getString("phone"));

                realtors.add(realtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realtors;
    }

    @Override
    public Realtor getRealtorById(long realtorId) {
        Realtor realtor = null;

        String query = "SELECT first_name, last_name, middle_name, phone, email, street_id, building_number, " +
                "premise_number, specialization_id, working_status_id, level_id, start_date " +
                "FROM realtor WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, realtorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    realtor = new Realtor();
                    realtor.setId(realtorId);
                    realtor.setFirstName(resultSet.getString("first_name"));
                    realtor.setLastName(resultSet.getString("last_name"));
                    realtor.setMiddleName(resultSet.getString("middle_name"));
                    realtor.setPhone(resultSet.getString("phone"));
                    realtor.setEmail(resultSet.getString("email"));
                    realtor.setStreetId(resultSet.getLong("street_id"));
                    realtor.setBuildingNumber(resultSet.getString("building_number"));
                    realtor.setPremiseNumber(resultSet.getInt("premise_number"));
                    realtor.setSpecializationId(resultSet.getLong("specialization_id"));
                    realtor.setWorkingStatusId(resultSet.getLong("working_status_id"));
                    realtor.setLevelId(resultSet.getLong("level_id"));
                    realtor.setStartDate(resultSet.getDate("start_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realtor;
    }

    @Override
    public Map<Long, String> getStreets() {
        Map<Long, String> streets = new HashMap<>();
        String query = "SELECT id, street_name FROM street";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                streets.put(resultSet.getLong("id"), resultSet.getString("street_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return streets;
    }

    @Override
    public Map<Long, String> getSpecializations() {
        Map<Long, String> specializations = new HashMap<>();
        String query = "SELECT id, specialization_name FROM specialization";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                specializations.put(resultSet.getLong("id"), resultSet.getString("specialization_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specializations;
    }

    @Override
    public Map<Long, String> getWorkingStatuses() {
        Map<Long, String> workingStatuses = new HashMap<>();
        String query = "SELECT id, status_name FROM working_status";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                workingStatuses.put(resultSet.getLong("id"), resultSet.getString("status_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workingStatuses;
    }

    @Override
    public Map<Long, String> getLevels() {
        Map<Long, String> levels = new HashMap<>();
        String query = "SELECT id, level_name FROM level";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                levels.put(resultSet.getLong("id"), resultSet.getString("level_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return levels;
    }

    @Override
    public void updateRealtor(Realtor realtor) {
        List<Realtor> realtors = getAllRealtors();
        String email = realtor.getEmail();

        if (realtors.stream()
                .anyMatch(realtor1 -> realtor1.getEmail().equals(email) && realtor1.getId() != realtor.getId())) {
            throw new IllegalArgumentException("The realtor with this email already exists: " + email);
        }


        String query = "UPDATE realtor SET first_name = ?, last_name = ?, middle_name = ?, phone = ?, email = ?, " +
                "street_id = ?, building_number = ?, premise_number = ?, specialization_id = ?, " +
                "working_status_id = ?, level_id = ?, start_date = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, realtor.getFirstName());
            preparedStatement.setString(2, realtor.getLastName());
            preparedStatement.setString(3, realtor.getMiddleName());
            preparedStatement.setString(4, realtor.getPhone());
            preparedStatement.setString(5, realtor.getEmail());
            preparedStatement.setLong(6, realtor.getStreetId());
            preparedStatement.setString(7, realtor.getBuildingNumber());
            preparedStatement.setInt(8, realtor.getPremiseNumber());
            preparedStatement.setLong(9, realtor.getSpecializationId());
            preparedStatement.setLong(10, realtor.getWorkingStatusId());
            preparedStatement.setLong(11, realtor.getLevelId());
            preparedStatement.setDate(12, new java.sql.Date(realtor.getStartDate().getTime()));
            preparedStatement.setLong(13, realtor.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createRealtor(Realtor realtor) {
        List<Realtor> realtors = getAllRealtors();
        String email = realtor.getEmail();

        if (realtors.stream()
                .anyMatch(realtor1 -> realtor1.getEmail().equals(email))) {
            throw new IllegalArgumentException("A realtor with this email already exists: " + email);
        }

        String query = "INSERT INTO realtor (street_id, building_number, premise_number, working_status_id, level_id, " +
                "specialization_id, first_name, last_name, middle_name, phone, email, start_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, realtor.getStreetId());
            preparedStatement.setString(2, realtor.getBuildingNumber());
            preparedStatement.setInt(3, realtor.getPremiseNumber());
            preparedStatement.setLong(4, realtor.getWorkingStatusId());
            preparedStatement.setLong(5, realtor.getLevelId());
            preparedStatement.setLong(6, realtor.getSpecializationId());
            preparedStatement.setString(7, realtor.getFirstName());
            preparedStatement.setString(8, realtor.getLastName());
            preparedStatement.setString(9, realtor.getMiddleName());
            preparedStatement.setString(10, realtor.getPhone());
            preparedStatement.setString(11, realtor.getEmail());
            preparedStatement.setDate(12, Date.valueOf(String.valueOf(realtor.getStartDate())));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRealtor(long id) throws SQLIntegrityConstraintViolationException {
        String query = "DELETE FROM realtor WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw new SQLIntegrityConstraintViolationException("Cannot delete realtor: foreign key constraint violation", e);
            } else {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Realtor> searchRealtors(String searchQuery) throws SQLException {
        List<Realtor> realtors = new ArrayList<>();
        String query = "SELECT r.id, r.email, r.phone, r.first_name, " +
                "r.last_name, r.middle_name, CONCAT(r.first_name,' ', r.middle_name, ' ', r.last_name) AS full_name, " +
                "CONCAT(s.street_name, ', ', r.building_number, ', кв. ', r.premise_number) AS address, " +
                "ls.level_name AS level, ss.specialization_name AS specialization, ws.status_name AS working_status, r.start_date " +
                "FROM realtor r " +
                "JOIN street s ON r.street_id = s.id " +
                "JOIN level ls ON r.level_id = ls.id " +
                "JOIN specialization ss ON r.specialization_id = ss.id " +
                "JOIN working_status ws ON r.working_status_id = ws.id " +
                "WHERE CONCAT(r.first_name, ' ', r.middle_name, ' ', r.last_name) LIKE ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchQuery + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Realtor realtor = new Realtor();
                realtor.setId(resultSet.getLong("id"));
                realtor.setFullName(resultSet.getString("full_name"));
                realtor.setFirstName(resultSet.getString("first_name"));
                realtor.setMiddleName(resultSet.getString("middle_name"));
                realtor.setLastName(resultSet.getString("last_name"));
                realtor.setAddress(resultSet.getString("address"));
                realtor.setLevel(resultSet.getString("level"));
                realtor.setSpecialization(resultSet.getString("specialization"));
                realtor.setWorkingStatus(resultSet.getString("working_status"));
                realtor.setStartDate(resultSet.getDate("start_date"));
                realtor.setEmail(resultSet.getString("email"));
                realtor.setPhone(resultSet.getString("phone"));
                realtors.add(realtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realtors;
    }
}
