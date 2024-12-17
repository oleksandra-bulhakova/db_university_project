<%@ page import="org.commercial_real_estate.model.entities.Demonstration" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Демонстрації об'єктів</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary mb-3">На головну</a>
  <a href="demonstrations/create-demo" class="btn btn-primary mb-3 float-right">Додати новий показ</a>
</div>
<h1 class="container">Всі покази</h1>
<div class="container mt-3">
  <div class="alert alert-info" role="alert">
    <%List<Demonstration> deals0 = (List<Demonstration>) request.getAttribute("demonstrations");%>
    <strong>Всього показів: </strong><%= (deals0 != null) ? deals0.size() : 0 %>
  </div>
</div>
<div class="container mt-3">
  <table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark sticky-top">
    <tr>
      <th>Дата</th>
      <th>Статус</th>
      <th>Адреса об'єкта</th>
      <th>Орендар</th>
      <th>Рієлтор</th>
      <th>Дії</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Demonstration> demonstrations = (List<Demonstration>) request.getAttribute("demonstrations");
      if (demonstrations != null && !demonstrations.isEmpty()) {
        for (Demonstration demonstration : demonstrations) {
    %>
    <tr>
      <td><%= demonstration.getDate() %></td>
      <td><%= demonstration.getDemoStatus() %></td>
      <td><%= demonstration.getObjectAddress() %></td>
      <td><%= demonstration.getTenantFullName() %></td>
      <td><%= demonstration.getRealtorFullName() %></td>
      <td>
        <a href="edit-demo?id=<%= demonstration.getId() %>" class="btn btn-warning btn-sm">Редагувати</a>
        <br>
        <a href="demonstrations/delete?id=<%= demonstration.getId() %>" class="btn btn-danger btn-sm mt-1">Видалити</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="6">Немає даних</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>