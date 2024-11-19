<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/19/2024
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.commercial_real_estate.model.RealEstateObject" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Real Estate Objects</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="add-real-estate-object" class="btn btn-primary mb-3 float-right mr-0">
        Додати новий об'єкт
    </a>
</div>
<h1 class="container">Всі об'єкти нерухомості</h1>
<table class="table table-bordered table-light" style="max-width: 90%; margin: 0 auto">
    <thead>
    <tr>
        <th>ID</th>
        <th>Назва вулиці</th>
        <th>Owner ID</th>
        <th>Object Type ID</th>
        <th>Площа, м. кв.</th>
        <th>Поверх</th>
        <th>Наявність ремонту</th>
        <th>Наявність меблів</th>
        <th>Ціна, грн./міс.</th>
        <th>Номер будинку</th>
        <th>Номер приміщення</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<RealEstateObject> realEstateObjects = (List<RealEstateObject>) request.getAttribute("realEstateObjects");
        if (realEstateObjects != null && !realEstateObjects.isEmpty()) {
            for (RealEstateObject object : realEstateObjects) {
    %>
    <tr>
        <td><%= object.getId() %></td>
        <td><%= object.getStreetName() %></td>
        <td><%= object.getOwnerId() %></td>
        <td><%= object.getObjectTypeId() %></td>
        <td><%= object.getArea() %></td>
        <td><%= object.getFloor() %></td>
        <td><%= object.isRenovation() ? "Так" : "Ні" %></td>
        <td><%= object.isFurniture() ? "Так" : "Ні" %></td>
        <td><%= object.getPrice() %></td>
        <td><%= object.getBuildingNumber() %></td>
        <td><%= object.getPremiseNumber() %></td>
        <td>
            <a href="read-real-estate-object?id=<%= object.getId() %>">Read</a>
            <a href="edit-real-estate-object?id=<%= object.getId() %>">Edit</a>
            <a href="delete-real-estate-object?id=<%= object.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="12">No data available</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>