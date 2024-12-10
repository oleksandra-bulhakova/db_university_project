package org.commercial_real_estate.repository.stat;

import org.commercial_real_estate.model.Realtor;
import org.commercial_real_estate.model.RealtorStatistics;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class StatisticsDAOImpl {

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

    public Map<String, Integer> getSourcesStatistics(Date startDate, Date endDate) {
        Map<String, Integer> map = new LinkedHashMap<>();
        String query = "SELECT \n" +
                "    asrc.source_name AS source, \n" +
                "    COUNT(ro.id) AS object_count\n" +
                "FROM \n" +
                "    real_estate_object ro\n" +
                "JOIN \n" +
                "    owner o ON ro.owner_id = o.id\n" +
                "JOIN \n" +
                "    acquisition_source asrc ON o.acquisition_source_id = asrc.id\n" +
                "WHERE \n" +
                "    o.acquisition_date BETWEEN ? AND ?\n" +
                "GROUP BY \n" +
                "    asrc.source_name \n" +
                "ORDER BY \n" +
                "object_count DESC;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    map.put(resultSet.getString("source"), resultSet.getInt("object_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<RealtorStatistics> getDealsStatistics(Date startDate, Date endDate) {
        List<RealtorStatistics> result = new ArrayList<>();
        String query = "SELECT " +
                "    r.first_name, " +
                "    r.last_name, " +
                "    COUNT(d.id) AS total_deals, " +
                "    AVG(ro.price) AS avg_amount, " +
                "    AVG(ro.area) AS avg_area " +
                "FROM " +
                "    deal d " +
                "JOIN " +
                "    realtor r ON d.realtor_id = r.id " +
                "JOIN " +
                "    real_estate_object ro ON d.object_id = ro.id " +
                "JOIN " +
                "    deal_status ds ON d.deal_status_id = ds.id " +
                "WHERE " +
                "    d.date BETWEEN ? AND ? " +
                "    AND ds.name = 'УГОДУ УКЛАДЕНО' " +
                "GROUP BY " +
                "    r.first_name, r.last_name " +
                "ORDER BY " +
                "    total_deals DESC";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RealtorStatistics stats = new RealtorStatistics();
                    stats.setFirstName(resultSet.getString("first_name"));
                    stats.setLastName(resultSet.getString("last_name"));
                    stats.setTotalDeals(resultSet.getInt("total_deals"));
                    stats.setAvgAmount(resultSet.getDouble("avg_amount"));
                    stats.setAvgArea(resultSet.getDouble("avg_area"));

                    result.add(stats);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
