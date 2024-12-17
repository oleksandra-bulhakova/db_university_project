<%@ page import="org.commercial_real_estate.model.entities.Realtor" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Створити рієлтора</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/realtors" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
  <h1 class="container">Створити рієлтора</h1>
  <div class="container mt-3">
    <form method="post" action="create-realtor">
      <% Realtor realtor = (Realtor) request.getAttribute("realtor"); %>

      <div class="form-group mb-3">
        <label for="firstName">Ім'я:</label>
        <input type="text" id="firstName" name="firstName" required>
      </div>

      <div class="form-group mb-3">
        <label for="lastName">Прізвище:</label>
        <input type="text" id="lastName" name="lastName" required>
      </div>

      <div class="form-group mb-3">
        <label for="middleName">По батькові:</label>
        <input type="text" id="middleName" name="middleName" required>
      </div>

      <div class="form-group mb-3">
        <label for="phone">Телефон:</label>
        <input type="text" id="phone" name="phone" required>
      </div>

      <div class="form-group mb-3">
        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" required>
      </div>

      <div class="form-group mb-3">
        <label for="streetId">Вулиця:</label>
        <select id="streetId" name="streetId" required>
          <%
            Map<Long, String> streets = (Map<Long, String>) request.getAttribute("streets");
            for (Map.Entry<Long, String> street : streets.entrySet()) {
              long streetId = street.getKey();
              String streetName = street.getValue();
          %>
          %>
          <option value="<%= streetId %>"><%= streetName %></option>
          <% } %>
        </select>
      </div>

      <div class="form-group mb-3">
        <label for="specializationId">Спеціалізація:</label>
        <select id="specializationId" name="specializationId" required>
          <%
            Map<Long, String> specializations = (Map<Long, String>) request.getAttribute("specializations");
            for (Map.Entry<Long, String> specialization : specializations.entrySet()) {
              long specializationId = specialization.getKey();
              String specializationName = specialization.getValue();
          %>
          <option value="<%= specializationId %>"><%= specializationName %></option>
          <% } %>
        </select>
      </div>

      <div class="form-group mb-3">
        <label for="workingStatusId">Статус роботи:</label>
        <select id="workingStatusId" name="workingStatusId" required>
          <%
            Map<Long, String> workingStatuses = (Map<Long, String>) request.getAttribute("workingStatuses");
            for (Map.Entry<Long, String> workingStatus : workingStatuses.entrySet()) {
              long workingStatusId = workingStatus.getKey();
              String statusName = workingStatus.getValue();
          %>
          <option value="<%= workingStatusId %>"><%= statusName %></option>
          <% } %>
        </select>
      </div>

      <div class="form-group mb-3">
        <label for="levelId">Сіньорити:</label>
        <select id="levelId" name="levelId" required>
          <%
            Map<Long, String> levels = (Map<Long, String>) request.getAttribute("levels");
            for (Map.Entry<Long, String> level : levels.entrySet()) {
              long levelId = level.getKey();
              String levelName = level.getValue();
          %>
          <option value="<%= levelId %>"><%= levelName %></option>
          <% } %>
        </select>
      </div>

      <div class="form-group mb-3">
        <label for="buildingNumber">Номер будинку:</label>
        <input type="text" id="buildingNumber" name="buildingNumber" required>
      </div>

      <div class="form-group mb-3">
        <label for="premiseNumber">Номер квартири:</label>
        <input type="number" id="premiseNumber" name="premiseNumber" required>
      </div>

      <div class="form-group mb-3">
        <label for="startDate">Дата початку:</label>
        <input type="date" id="startDate" name="startDate" required>
      </div>

      <button type="submit" class="btn btn-primary">Зберегти</button>
    </form>
  </div>
</div>
</body>
</html>
