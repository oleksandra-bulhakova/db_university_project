<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/20/2024
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.commercial_real_estate.model.Realtor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ріелтори</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary mb-3">На головну</a>
  <a href="add-realtor" class="btn btn-primary mb-3 float-right">Додати нового ріелтора</a>
</div>
<h1 class="container">Всі рієлтори</h1>
<div class="container mt-3">
  <table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark sticky-top">
    <tr>
      <th>ПІБ</th>
      <th>Адреса</th>
      <th>Рівень</th>
      <th>Спеціалізація</th>
      <th>Статус роботи</th>
      <th>Дата початку роботи в компанії</th>
      <th>Дії</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Realtor> realtors = (List<Realtor>) request.getAttribute("realtors");
      if (realtors != null && !realtors.isEmpty()) {
        for (Realtor realtor : realtors) {
    %>
    <tr>
      <td><%= realtor.getFullName() %></td>
      <td><%= realtor.getAddress() %></td>
      <td><%= realtor.getLevel() %></td>
      <td><%= realtor.getSpecialization() %></td>
      <td><%= realtor.getWorkingStatus() %></td>
      <td><%= realtor.getStartDate() %></td>
      <td>
        <a href="edit-realtor?id=<%= realtor.getId() %>" class="btn btn-warning btn-sm">Edit</a>
        <br>
        <a href="delete-realtor?id=<%= realtor.getId() %>" class="btn btn-danger btn-sm mt-1">Delete</a>
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