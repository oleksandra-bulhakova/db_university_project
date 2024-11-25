<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/22/2024
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.commercial_real_estate.model.Realtor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Редагувати рієлтора</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="/realtors" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
    <h1 class="container">Редагувати рієлтора</h1>
    <div class="container mt-3">
        <form method="post" action="edit-realtor">
            <% Realtor realtor = (Realtor) request.getAttribute("realtor"); %>
            <input type="hidden" name="id" value="<%= realtor.getId() %>">

            <div class="form-group mb-3">
                <label for="firstName">Ім'я:</label>
                <input type="text" id="firstName" name="firstName" value="<%= realtor.getFirstName() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="lastName">Прізвище:</label>
                <input type="text" id="lastName" name="lastName" value="<%= realtor.getLastName() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="middleName">По батькові:</label>
                <input type="text" id="middleName" name="middleName" value="<%= realtor.getMiddleName() %>">
            </div>

            <div class="form-group mb-3">
                <label for="phone">Телефон:</label>
                <input type="text" id="phone" name="phone" value="<%= realtor.getPhone() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" value="<%= realtor.getEmail() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="streetId">Вулиця:</label>
                <select id="streetId" name="streetId" required>
                    <%
                        Map<Long, String> streets = (Map<Long, String>) request.getAttribute("streets");
                        for (Map.Entry<Long, String> street : streets.entrySet()) {
                            long streetId = street.getKey();
                            String streetName = street.getValue();
                            String selected = streetId == realtor.getStreetId() ? "selected" : "";
                    %>
                    %>
                    <option value="<%= streetId %>" <%= selected %>><%= streetName %></option>
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
                            String selected = specializationId == realtor.getSpecializationId() ? "selected" : "";
                    %>
                    <option value="<%= specializationId %>" <%= selected %>><%= specializationName %></option>
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
                            String selected = workingStatusId == realtor.getWorkingStatusId() ? "selected" : "";
                    %>
                    <option value="<%= workingStatusId %>" <%= selected %>><%= statusName %></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group mb-3">
                <label for="levelId">Сеньорити:</label>
                <select id="levelId" name="levelId" required>
                    <%
                        Map<Long, String> levels = (Map<Long, String>) request.getAttribute("levels");
                        for (Map.Entry<Long, String> level : levels.entrySet()) {
                            long levelId = level.getKey();
                            String levelName = level.getValue();
                            String selected = levelId == realtor.getLevelId() ? "selected" : "";
                    %>
                    <option value="<%= levelId %>" <%= selected %>><%= levelName %></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group mb-3">
                <label for="buildingNumber">Номер будинку:</label>
                <input type="text" id="buildingNumber" name="buildingNumber" value="<%= realtor.getBuildingNumber() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="premiseNumber">Номер квартири:</label>
                <input type="number" id="premiseNumber" name="premiseNumber" value="<%= realtor.getPremiseNumber() %>" required>
            </div>

            <div class="form-group mb-3">
                <label for="startDate">Дата початку:</label>
                <input type="date" id="startDate" name="startDate" value="<%= realtor.getStartDate() %>" required>
            </div>

            <button type="submit" class="btn btn-primary">Зберегти</button>
        </form>
    </div>
</div>
</body>
</html>
