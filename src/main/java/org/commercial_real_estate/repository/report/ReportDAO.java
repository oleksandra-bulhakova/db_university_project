package org.commercial_real_estate.repository.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDate;

@Slf4j
public class ReportDAO {
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

    public void generateReport(Date startDate, Date endDate) throws Exception {
        String sql = "SELECT\n" +
                "        CONCAT(r.first_name, ' ', r.last_name) AS realtor_full_name,\n" +
                "        l.level_name AS realtor_level,\n" +
                "        CONCAT(st.street_name, ', ', re.building_number, ' прим. ', re.premise_number) AS object,\n" +
                "        dist.district_name AS district_name,\n" +
                "        d.price AS deal_price,\n" +
                "        src.source_name AS source_name\n" +
                "     FROM\n" +
                "        deal d\n" +
                "     JOIN\n" +
                "        deal_status ds ON d.deal_status_id = ds.id\n" +
                "     JOIN\n" +
                "        realtor r ON d.realtor_id = r.id\n" +
                "     JOIN\n" +
                "        real_estate_object re ON d.object_id = re.id\n" +
                "     JOIN\n" +
                "        owner o ON re.owner_id = o.id\n" +
                "     JOIN\n" +
                "        street st ON re.street_id = st.id\n" +
                "     JOIN\n" +
                "        district dist ON st.district_id = dist.id\n" +
                "     JOIN\n" +
                "        level l ON l.id = r.level_id\n" +
                "     LEFT JOIN\n" +
                "        acquisition_source src ON o.acquisition_source_id = src.id\n" +
                "     WHERE\n" +
                "        ds.name = 'СКАСОВАНО'\n" +
                "        AND d.date BETWEEN ? AND ?\n" +
                "     ORDER BY\n" +
                "        d.date";

        File reportDir = new File("C:/Users/o.bulhakova/IdeaProjects/db_university_project/report");
        File outputFile = new File(reportDir, "deals_report.pdf");
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();
        BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 14, Font.NORMAL, BaseColor.BLACK);
        Font font1 = new Font(baseFont, 14, Font.BOLD, BaseColor.BLACK);
        Font font2 = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);

        document.add(new Paragraph("Звіт про скасовані угоди", font1));
        document.add(new Paragraph("Період: " + startDate + " - " + endDate, font));
        document.add(new Paragraph(("Дата генерації: " + LocalDate.now()), font));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell(new Phrase("Рієлтор", font));
        table.addCell(new Phrase("Рівень", font));
        table.addCell(new Phrase("Об'єкт", font));
        table.addCell(new Phrase("Ціна угоди, грн./міс.", font));
        table.addCell(new Phrase("Район", font));
        table.addCell(new Phrase("Джерело залучення", font));

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String realtorFullName = resultSet.getString("realtor_full_name");
                    String realtorLevel = resultSet.getString("realtor_level");
                    double dealPrice = resultSet.getDouble("deal_price");
                    String districtName = resultSet.getString("district_name");
                    String sourceName = resultSet.getString("source_name");
                    String object = resultSet.getString("object");

                    table.addCell(new Phrase(realtorFullName, font2));
                    table.addCell(new Phrase(realtorLevel, font2));
                    table.addCell(new Phrase(object, font2));
                    table.addCell(new Phrase(String.valueOf(dealPrice), font2));
                    table.addCell(new Phrase(districtName, font2));
                    table.addCell(new Phrase(sourceName, font2));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document.add(table);
        document.close();
    }

    public void generateDemandReport(Date startDate, Date endDate) throws Exception {
        String sql = "SELECT \n" +
                "    ot.object_type_name AS object_type,\n" +
                "    d.district_name AS district,\n" +
                "    COUNT(t.id) AS total_requests,\n" +
                "    AVG(t.budget) AS average_budget,\n" +
                "    AVG(t.desired_area) AS average_area\n" +
                "FROM \n" +
                "    tenant t\n" +
                "JOIN \n" +
                "    object_type ot ON t.desired_object_type_id = ot.id\n" +
                "JOIN \n" +
                "    district d ON t.desired_district_id = d.id\n" +
                "WHERE \n" +
                "    t.acquisition_date BETWEEN ? AND ?\n" +
                "GROUP BY \n" +
                "    ot.object_type_name, d.district_name\n" +
                "ORDER BY \n" +
                "    total_requests DESC;";

        File reportDir = new File("C:/Users/o.bulhakova/IdeaProjects/db_university_project/report");
        File outputFile = new File(reportDir, "demand_report.pdf");
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();
        BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 14, Font.NORMAL, BaseColor.BLACK);
        Font font1 = new Font(baseFont, 14, Font.BOLD, BaseColor.BLACK);
        Font font2 = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);

        document.add(new Paragraph("Звіт про запити орендарів", font1));
        document.add(new Paragraph("Період: " + startDate + " - " + endDate, font));
        document.add(new Paragraph(("Дата генерації: " + LocalDate.now()), font));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.addCell(new Phrase("Тип об'єкта", font));
        table.addCell(new Phrase("Район", font));
        table.addCell(new Phrase("Кількість запитів", font));
        table.addCell(new Phrase("Середній бюджет, грн./міс.", font));
        table.addCell(new Phrase("Середня площа, м²", font));

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String objectType = resultSet.getString("object_type");
                    String district = resultSet.getString("district");
                    int totalRequests = resultSet.getInt("total_requests");
                    double averageBudget = resultSet.getDouble("average_budget");
                    double averageArea = resultSet.getDouble("average_area");

                    table.addCell(new Phrase(objectType, font2));
                    table.addCell(new Phrase(district, font2));
                    table.addCell(new Phrase(String.valueOf(totalRequests), font2));
                    table.addCell(new Phrase(String.valueOf(averageBudget), font2));
                    table.addCell(new Phrase(String.valueOf(averageArea), font2));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document.add(table);
        document.close();
    }
}
