<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.commercial_real_estate.model.entities.Deal" %>
<%@ page import="java.util.Map" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Створити угоду</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"></head>
<body>
<div class="container mt-3">
  <a href="/deals" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
  <h1 class="container">Створити угоду</h1>
  <div class="container mt-3">
    <form method="post" action="create-deal">
      <%
        Deal deal = (Deal) request.getAttribute("deal");
        Map<Long, String> objects = (Map<Long, String>) request.getAttribute("objects");
        Map<Long, String> statuses = (Map<Long, String>) request.getAttribute("statuses");
        Map<Long, String> tenants = (Map<Long, String>) request.getAttribute("tenants");
        Map<Long, String> realtors = (Map<Long, String>) request.getAttribute("realtors");
      %>
      <div class="form-group mb-3">
        <label for="object">Об'єкт:</label>
        <select id="object" name="object" required>
          <%
            for (Map.Entry<Long, String> obj : objects.entrySet()) {
              long objectId = obj.getKey();
              String objectName = obj.getValue();
          %>
          <option value="<%=objectId%>"><%= objectName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="dealDate">Дата угоди:</label>
        <input type="date" id="dealDate" name="dealDate" value="<%= deal.getDate() %>" required>
      </div>
      <div class="form-group mb-3">
        <label for="status">Статус угоди:</label>
        <select id="status" name="status" required>
          <%
            for (Map.Entry<Long, String> stat : statuses.entrySet()) {
              long statusId = stat.getKey();
              String statusName = stat.getValue();
          %>
          <option value="<%=statusId%>"><%= statusName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="tenant">Орендар:</label>
        <select id="tenant" name="tenant" required>
          <%
            for (Map.Entry<Long, String> tenant : tenants.entrySet()) {
              long tenantId = tenant.getKey();
              String tenantName = tenant.getValue();
          %>
          <option value="<%=tenantId%>"><%= tenantName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="realtor">Рієлтор:</label>
        <select id="realtor" name="realtor" required>
          <%
            for (Map.Entry<Long, String> realtor : realtors.entrySet()) {
              long realtorId = realtor.getKey();
              String realtorName = realtor.getValue();
          %>
          <option value="<%=realtorId%>"><%= realtorName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="dealPrice">Ціна:</label>
        <input type="text" id="dealPrice" name="dealPrice" value="<%= deal.getPrice() %>" required>
      </div>
      <button type="submit" class="btn btn-primary">Зберегти</button>
    </form>
  </div>
</div>
</body>
</html>
