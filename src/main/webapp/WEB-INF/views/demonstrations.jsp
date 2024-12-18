<%@ page import="org.commercial_real_estate.model.entities.Demonstration" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Демонстрації об'єктів</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    table {
      width: 100%;
    }
    .form {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      margin-top: 10px;
      margin-left: 0;
      padding-left: 0;
      box-sizing: border-box;
    }
    .form input, .form button {
      margin: 0 3px;
      padding: 10px 20px;
      font-size: 16px;
      display: inline-flex;
      justify-content: center;
      align-items: center;
      line-height: normal;
      text-align: center;
    }
    .form button {
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button, .btn {
      height: 38px;
      width: 100px;
      padding: 0 15px;
      line-height: 38px;
    }
    .btn-wide {
      width: auto;
    }
    .startDateInput {
      margin-left: 0 !important;
      padding: 10px 20px;
      font-size: 16px;
      display: inline-flex;
      justify-content: center;
      align-items: center;
      line-height: normal;
      text-align: center;
    }
  </style>
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
  <a href="demonstrations/create-demo" class="btn btn-primary btn-wide mb-3 float-right">Додати новий показ</a>
</div>
<h1 class="container">Всі покази</h1>
<div class="container mt-3">
  <div class="alert alert-info" role="alert">
    <%List<Demonstration> deals0 = (List<Demonstration>) request.getAttribute("demonstrations");%>
    <strong>Всього показів: </strong><%= (deals0 != null) ? deals0.size() : 0 %>
  </div>
</div>
<div class="container mt-3">

  <form class="form" action="demonstrations" method="get">
    <input type="hidden" name="filterType" value="dates">
    <label for="startDate"></label>
    <input type="date" id="startDate" name="startDate" class="startDateInput" value="<%= request.getParameter("startDate") != null ? request.getParameter("startDate") : "" %>" required>
    <label for="endDate"></label>
    <input type="date" id="endDate" name="endDate" value="<%= request.getParameter("endDate") != null ? request.getParameter("endDate") : "" %>" required>
    <button class="submit" type="submit">Знайти</button>
    <a href="demonstrations" class="btn btn-info">Скинути</a>
  </form>

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
      <td colspan="6">Немає підходящих даних</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>