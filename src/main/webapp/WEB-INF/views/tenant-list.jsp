<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/20/2024
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.commercial_real_estate.model.Tenant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Арендаторы</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary mb-3">На головну</a>
  <a href="add-tenant" class="btn btn-primary mb-3 float-right">Додати нового арендатора</a>
</div>
<h1 class="container">Всі орендатори</h1>
<div class="container mt-3">
  <table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark sticky-top">
    <tr>
      <th>ПІБ</th>
      <th>Адреса</th>
      <th>Телефон</th>
      <th>Email</th>
      <th>Дата залучення</th>
      <th>Джерело залучення</th>
      <th>Дії</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Tenant> tenants = (List<Tenant>) request.getAttribute("tenants");
      if (tenants != null && !tenants.isEmpty()) {
        for (Tenant tenant : tenants) {
    %>
    <tr>
      <td><%= tenant.getFirstName() + " " + tenant.getMiddleName() + " " + tenant.getLastName() %></td>
      <td><%= tenant.getStreetName() + ", " + tenant.getBuildingNumber() + ", кв. " + tenant.getPremiseNumber() %></td>
      <td><%= tenant.getPhone() %></td>
      <td><%= tenant.getEmail() %></td>
      <td><%= tenant.getAcquisitionDate() %></td>
      <td><%= tenant.getAcquisitionSourceName() %></td>
      <td>
        <a href="edit-tenant?id=<%= tenant.getId() %>" class="btn btn-warning btn-sm">Edit</a>
        <br>
        <a href="delete-tenant?id=<%= tenant.getId() %>" class="btn btn-danger btn-sm mt-1">Delete</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="7">Немає даних</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>
