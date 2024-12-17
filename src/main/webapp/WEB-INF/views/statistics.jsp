<%@ page import="java.util.Map" %>
<%@ page import="org.commercial_real_estate.model.serviceObjects.RealtorStatistics" %>
<%@ page import="java.util.List" %>
<%@ page import="org.commercial_real_estate.model.serviceObjects.DistrictStatistics" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Статистика</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        .statistics th, .statistics td {
            padding: 10px;
            text-align: center;
        }

        .container {
            width: 90%;
            margin: 0 auto;
            padding: 0px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            padding-bottom: 20px;
        }

        .item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        .item-center {
            grid-column: span 2;
        }

        .form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .form input, .form button {
            margin: 0 10px;
            padding: 10px;
            font-size: 14px;
        }

        .form button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form button:hover {
            background-color: #0056b3;
        }

        .statistics {
            margin-top: 30px;
        }

        .statistics h2 {
            text-align: center;
            color: #555;
        }

        .statistics table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .statistics table, .statistics th, .statistics td {
            border: 1px solid #ddd;
        }

        .statistics td:first-child {
            text-align: left;
        }

        .statistics th {
            background-color: #fff9c4;
        }
    </style>
</head>
<body>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
</div>
<div class="container">
    <h1>Статистика</h1>
    <div class="grid">
        <%--        <div class="item">--%>
        <div class="item">
            <h3>За укладеними угодами рієлторів</h3>
            <form class="form" method="post" action="stats">
                <input type="hidden" name="statisticsType" value="realtor">
                <input type="date" name="startDate1"
                       value="<%= request.getParameter("startDate1") != null ? request.getParameter("startDate1") : "" %>"
                       required>
                <input type="date" name="endDate1"
                       value="<%= request.getParameter("endDate1") != null ? request.getParameter("endDate1") : "" %>"
                       required>
                <button type="submit">Показати статистику</button>
            </form>

            <div class="statistics">
                <h2>Результати</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Рієлтор</th>
                        <th>Кількість угод</th>
                        <th>Серед. сума угоди, грн./міс.</th>
                        <th>Серед. площа об'єкта, м²</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<RealtorStatistics> realtorStats = (List<RealtorStatistics>) request.getAttribute("realtorStatistics");
                        if (realtorStats != null) {
                            for (RealtorStatistics stat : realtorStats) {
                    %>
                    <tr>
                        <td><%= stat.getFirstName() + " " + stat.getLastName() %>
                        </td>
                        <td><%= stat.getTotalDeals() %>
                        </td>
                        <td><%= stat.getAvgAmount() %>
                        </td>
                        <td><%= stat.getAvgArea() %>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
            <%--            </div>--%>
        </div>
        <div class="item">
            <h3>За проведеними показами рієлторів</h3>
            <form class="form" method="post" action="stats">
                <input type="hidden" name="statisticsType" value="demo">
                <input type="date" name="startDate2"
                       value="<%= request.getParameter("startDate2") != null ? request.getParameter("startDate2") : "" %>"
                       required>
                <input type="date" name="endDate2"
                       value="<%= request.getParameter("endDate2") != null ? request.getParameter("endDate2") : "" %>"
                       required>
                <button type="submit">Показати статистику</button>
            </form>
            <div class="statistics">
                <h2>Результати</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Рієлтор</th>
                        <th>Проведено показів</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Map<String, Integer> stats2 = (Map<String, Integer>) request.getAttribute("demoStatistics");
                        if (stats2 != null) {
                            for (Map.Entry<String, Integer> entry : stats2.entrySet()) {
                    %>
                    <tr>
                        <td><%= entry.getKey() %>
                        </td>
                        <td><%= entry.getValue() %>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <%--<div class="item">Вікно 3</div>--%>
        <div class="item">
            <h3>За джерелами залучення клієнтів</h3>
            <form class="form" method="post" action="stats">
                <input type="hidden" name="statisticsType" value="source">
                <input type="date" name="startDate5"
                       value="<%= request.getParameter("startDate5") != null ? request.getParameter("startDate5") : "" %>"
                       required>
                <input type="date" name="endDate5"
                       value="<%= request.getParameter("endDate5") != null ? request.getParameter("endDate5") : "" %>"
                       required>
                <button type="submit">Показати статистику</button>
            </form>


            <div class="statistics">
                <h2>Результати</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Джерела</th>
                        <th>Залучено об'єктів в роботу</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Map<String, Integer> stats1 = (Map<String, Integer>) request.getAttribute("statistics");
                        if (stats1 != null) {
                            for (Map.Entry<String, Integer> entry : stats1.entrySet()) {
                    %>
                    <tr>
                        <td><%= entry.getKey() %>
                        </td>
                        <td><%= entry.getValue() %>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="item">
            <h3>За районами</h3>
            <form class="form" method="post" action="stats">
                <input type="hidden" name="statisticsType" value="district">
                <input type="date" name="startDate3"
                       value="<%= request.getParameter("startDate3") != null ? request.getParameter("startDate3") : "" %>"
                       required>
                <input type="date" name="endDate3"
                       value="<%= request.getParameter("endDate3") != null ? request.getParameter("endDate3") : "" %>"
                       required>
                <button type="submit">Показати статистику</button>
            </form>

            <div class="statistics">
                <h2>Результати</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Район</th>
                        <th>Кількість угод</th>
                        <th>Серед. сума угоди, грн./міс.</th>
                        <th>Показів до угоди</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<DistrictStatistics> districtStats = (List<DistrictStatistics>) request.getAttribute("districtStatistics");
                        if (districtStats != null) {
                            for (DistrictStatistics stat : districtStats) {
                    %>
                    <tr>
                        <td><%= stat.getDistrictName() %>
                        </td>
                        <td><%= stat.getTotalDeals() %>
                        </td>
                        <td><%= stat.getAverageDealPrice() %>
                        </td>
                        <td><%= stat.getAverageDemosPerDeal() %>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>