package org.commercial_real_estate.repository.stat;

import org.commercial_real_estate.model.serviceObjects.DistrictStatistics;
import org.commercial_real_estate.model.serviceObjects.RealtorStatistics;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class StatisticsDAO {

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
                "    AVG(d.price) AS avg_amount, " +
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

    public Map<String, Integer> getDemosStatistics(Date startDate, Date endDate) {
        Map<String, Integer> map = new LinkedHashMap<>();
        String query = "SELECT \n" +
                " CONCAT(r.first_name, ' ', r.last_name) AS realtor_name,\n" +
                "    COUNT(d.id) AS demonstration_count\n" +
                "FROM \n" +
                "    demonstration d\n" +
                "JOIN \n" +
                "    realtor r ON d.realtor_id = r.id\n" +
                "JOIN\n" +
                "    demo_status ds ON d.demo_status_id = ds.id\n" +
                "WHERE \n" +
                "    d.date BETWEEN ? AND ?" +
                "   AND ds.name = 'ПРОВЕДЕНО'" +
                "  GROUP BY \n" +
                "    r.id, r.first_name, r.last_name\n" +
                "ORDER BY \n" +
                "    demonstration_count DESC;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    map.put(resultSet.getString("realtor_name"), resultSet.getInt("demonstration_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<DistrictStatistics> getDistrictStatistics(Date startDate, Date endDate) {
        List<DistrictStatistics> result = new ArrayList<>();
        String query = "SELECT " +
                "    dis.district_name AS district, " +
                "    COUNT(dl.id) AS total_deals, " +
                "    ROUND(AVG(dl.price), 2) AS avg_deal_price, " +
                "    ROUND(AVG(demo_count.total_demos), 2) AS avg_demos_per_deal " +
                "FROM " +
                "    deal dl " +
                "JOIN " +
                "    real_estate_object reo ON dl.object_id = reo.id " +
                "JOIN " +
                "    street st ON reo.street_id = st.id " +
                "JOIN " +
                "    district dis ON st.district_id = dis.id " +
                "JOIN " +
                "    deal_status ds ON dl.deal_status_id = ds.id " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        d.object_id, " +
                "        COUNT(d.id) AS total_demos " +
                "    FROM " +
                "        demonstration d " +
                "    JOIN " +
                "        demo_status dms ON d.demo_status_id = dms.id " +
                "    WHERE " +
                "        dms.name = 'ПРОВЕДЕНО' " +
                "    GROUP BY " +
                "        d.object_id " +
                ") demo_count ON dl.object_id = demo_count.object_id " +
                "WHERE " +
                "    ds.name = 'УГОДУ УКЛАДЕНО' AND dl.date BETWEEN ? AND ? " +
                "GROUP BY " +
                "    dis.district_name " +
                "ORDER BY " +
                "    total_deals DESC;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    DistrictStatistics stats = new DistrictStatistics();
                    stats.setDistrictName(resultSet.getString("district"));
                    stats.setTotalDeals(resultSet.getInt("total_deals"));
                    stats.setAverageDealPrice(resultSet.getDouble("avg_deal_price"));
                    stats.setAverageDemosPerDeal(resultSet.getDouble("avg_demos_per_deal"));

                    result.add(stats);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
