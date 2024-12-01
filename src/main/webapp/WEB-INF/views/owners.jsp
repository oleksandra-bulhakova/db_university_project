<%@ page import="org.commercial_real_estate.model.Owner" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Власники</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary mb-3">На головну</a>
  <a href="/owners/create-owner" class="btn btn-primary mb-3 float-right">Додати нового власника</a>
</div>
<h1 class="container">Всі власники</h1>
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
      List<Owner> owners = (List<Owner>) request.getAttribute("owners");
      if (owners != null && !owners.isEmpty()) {
        for (Owner owner : owners) {
    %>
    <tr>
      <td><%= owner.getFirstName() + " " + owner.getMiddleName() + " " + owner.getLastName()%></td>
      <td><%= owner.getStreetName() + ", " + owner.getBuildingNumber() + ", кв. " + owner.getPremiseNumber() %></td>
      <td><%= owner.getPhone() %></td>
      <td><%= owner.getEmail() %></td>
      <td><%= owner.getAcquisitionDate() %></td>
      <td><%= owner.getAcquisitionSourceName() %></td>
      <td>
        <a href="edit-owner?id=<%= owner.getId() %>" class="btn btn-warning btn-sm">Редагувати</a>
        <br>
        <a href="owners/delete?id=<%= owner.getId() %>" class="btn btn-danger btn-sm mt-1">Видалити</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="8">Немає даних</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>
