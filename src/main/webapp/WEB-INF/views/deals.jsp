<%@ page import="org.commercial_real_estate.model.Deal" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Список угод</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary mb-3">На головну</a>
  <a href="deals/create-deal" class="btn btn-primary mb-3 float-right">Додати нову угоду</a>
</div>
<h1 class="container">Всі угоди</h1>
<div class="container mt-3">
  <div class="alert alert-info" role="alert">
    <%List<Deal> deals0 = (List<Deal>) request.getAttribute("deals");%>
    <strong>Всього угод: </strong><%= (deals0 != null) ? deals0.size() : 0 %>
    <br>
    <strong>Загальна сума угод: </strong>
    <%= (deals0 != null) ? deals0.stream().mapToInt(Deal::getPrice).sum() : 0 %> грн
  </div>
</div>
<div class="container mt-3">
  <table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark sticky-top">
    <tr>
      <th>Дата</th>
      <th>Статус угоди</th>
      <th>Адреса об'єкта</th>
      <th>Орендар</th>
      <th>Рієлтор</th>
      <th>Ціна</th>
      <th>Дії</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Deal> deals = (List<Deal>) request.getAttribute("deals");
      if (deals != null && !deals.isEmpty()) {
        for (Deal deal : deals) {
    %>
    <tr>
      <td><%= deal.getDate() %></td>
      <td><%= deal.getDealStatus() %></td>
      <td><%= deal.getObjectAddress() %></td>
      <td><%= deal.getTenantFullName() %></td>
      <td><%= deal.getRealtorFullName() %></td>
      <td><%= deal.getPrice() %></td>
      <td>
        <a href="edit-deal?id=<%= deal.getId() %>" class="btn btn-warning btn-sm">Редагувати</a>
        <br>
        <a href="deals/delete?id=<%= deal.getId() %>" class="btn btn-danger btn-sm mt-1">Видалити</a>
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