package org.commercial_real_estate.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDAO {
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

    public List<List<String>> executeQuery(String query) throws SQLException {
        List<List<String>> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            if (query.trim().toUpperCase().startsWith("SELECT")) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    List<String> headerRow = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        headerRow.add(metaData.getColumnLabel(i));
                    }
                    result.add(headerRow);

                    while (resultSet.next()) {
                        List<String> row = new ArrayList<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.add(resultSet.getString(i));
                        }
                        result.add(row);
                    }
                }
            } else {
                int rowsAffected = statement.executeUpdate(query);
                List<String> messageRow = new ArrayList<>();
                messageRow.add("Запит виконано, кількість змін: " + rowsAffected);
                result.add(messageRow);
            }
        }
        return result;
    }
}