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
    <a href="/main" class="btn btn-secondary mb-3">На головну</a>
    <a href="add-real-estate-object" class="btn btn-primary mb-3 float-right mr-0">
        Додати новий об'єкт
    </a>
</div>
<h1 class="container">Всі об'єкти нерухомості</h1>
<div class="container mt-3">
    <div class="alert alert-info" role="alert">
        <%List<RealEstateObject> obj = (List<RealEstateObject>) request.getAttribute("realEstateObjects");%>
        <strong>Всього об'єктів: </strong><%= (obj != null) ? obj.size() : 0 %>
        <br>
        <strong>Загальна сума: </strong>
        <%= (obj != null) ? obj.stream().mapToInt(RealEstateObject::getPrice).sum() : 0 %> грн
    </div>
</div>
<div class="container mt-3">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark sticky-top">
            <tr>
                <th>Адреса</th>
                <th>Власник</th>
                <th>Тип об'єкту</th>
                <th>Площа, м²</th>
                <th>Поверх</th>
                <th>Ремонт</th>
                <th>Меблі</th>
                <th>Ціна, грн./міс.</th>
                <th>Дії</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<RealEstateObject> realEstateObjects = (List<RealEstateObject>) request.getAttribute("realEstateObjects");
                if (realEstateObjects != null && !realEstateObjects.isEmpty()) {
                    for (RealEstateObject object : realEstateObjects) {
            %>
         <tr>
                <td><%= object.getAddress() %></td>
                <td><%= object.getOwnerFullName() %></td>
                <td><%= object.getObjectType()%></td>
                <td><%= object.getArea() %></td>
                <td><%= object.getFloor() %></td>
                <td><%= object.isRenovation() ? "Є" : "НЕМАЄ" %></td>
                <td><%= object.isFurniture() ? "Є" : "НЕМАЄ" %></td>
                <td><%= object.getPrice() %></td>
                <td>
                    <a href="edit-real-estate-object?id=<%= object.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                    <br>
                    <a href="delete-real-estate-object?id=<%= object.getId() %>" class="btn btn-danger btn-sm mt-1">Delete</a>
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
        </div>
        </body>
</html>