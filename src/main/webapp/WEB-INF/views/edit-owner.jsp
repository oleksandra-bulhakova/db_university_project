<%@ page import="org.commercial_real_estate.model.entities.Owner" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Редагувати власника</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="/owners" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
<h1 class="container">Редагувати власника</h1>
<div class="container mt-3">
    <form method="post" action="edit-owner">
        <%Owner owner = (Owner) request.getAttribute("owner");%>
        <input type="hidden" name="id" value="<%= owner.getId() %>">

        <div class="form-group mb-3">
            <label for="firstName">Ім'я:</label>
            <input type="text" id="firstName" name="firstName" value="<%= owner.getFirstName() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="lastName">Прізвище:</label>
            <input type="text" id="lastName" name="lastName" value="<%= owner.getLastName() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="middleName">По батькові:</label>
            <input type="text" id="middleName" name="middleName" value="<%= owner.getMiddleName() %>">
        </div>

        <div class="form-group mb-3">
            <label for="phone">Телефон:</label>
            <input type="text" id="phone" name="phone" value="<%= owner.getPhone() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" value="<%= owner.getEmail() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="streetId">Вулиця:</label>
            <select id="streetId" name="streetId" required>
                <%
                    for (Map<String, Object> street : (List<Map<String, Object>>) request.getAttribute("streets")) {
                        long streetId = (long) street.get("id");
                        String streetName = (String) street.get("name");
                        String selected = streetId == owner.getStreetId() ? "selected" : "";
                %>
                <option value="<%= streetId %>" <%= selected %>><%= streetName %></option>
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
                        String selected = sourceId == owner.getAcquisitionSourceId() ? "selected" : "";
                %>
                <option value="<%= sourceId %>" <%= selected %>><%= sourceName %></option>
                <% } %>
            </select>
        </div>

        <div class="form-group mb-3">
            <label for="buildingNumber">Номер будинку:</label>
            <input type="text" id="buildingNumber" name="buildingNumber" value="<%= owner.getBuildingNumber() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="premiseNumber">Номер квартири:</label>
            <input type="number" id="premiseNumber" name="premiseNumber" value="<%= owner.getPremiseNumber() %>" required>
        </div>

        <div class="form-group mb-3">
            <label for="acquisitionDate">Дата залучення:</label>
            <input type="date" id="acquisitionDate" name="acquisitionDate" value="<%= owner.getAcquisitionDate() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</div>
</body>
</html>
