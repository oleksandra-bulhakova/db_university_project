<%@ page import="org.commercial_real_estate.model.Owner" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Створити власника</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/owners" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
  <h1 class="container">Створити нового власника</h1>
  <div class="container mt-3">
    <form method="post" action="create-owner">
      <%Owner owner = (Owner) request.getAttribute("owner");%>

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
            for (Map<String, Object> street : (List<Map<String, Object>>) request.getAttribute("streets")) {
              long streetId = (long) street.get("id");
              String streetName = (String) street.get("name");
          %>
          <option value="<%= streetId %>"><%= streetName %></option>
          <% } %>
        </select>
      </div>

      <div class="form-group mb-3">
        <label for="acquisitionSourceId">Джерело залучення:</label>
        <select id="acquisitionSourceId" name="acquisitionSourceId" required>
          <%
            List<Map<String, Object>> sources = (List<Map<String, Object>>) request.getAttribute("acquisitionSources");
            for (Map<String, Object> source : sources) {
              long sourceId = (long) source.get("id");
              String sourceName = (String) source.get("name");
          %>
          <option value="<%= sourceId %>"><%= sourceName %></option>
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
        <label for="acquisitionDate">Дата залучення:</label>
        <input type="date" id="acquisitionDate" name="acquisitionDate" required>
      </div>

      <button type="submit" class="btn btn-primary">Save</button>
    </form>
  </div>
</div>
</body>
</html>
