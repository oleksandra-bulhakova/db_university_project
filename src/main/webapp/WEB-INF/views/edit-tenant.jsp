<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/22/2024
  Time: 6:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.commercial_real_estate.model.Tenant" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Редагувати орендаря</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="/tenants" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
    <h1 class="container">Редагувати орендаря</h1>
    <div class="container mt-3">
        <form method="post" action="edit-tenant">
            <%
                Tenant tenant = (Tenant) request.getAttribute("tenant");
                Map<Long, String> streets = (Map<Long, String>) request.getAttribute("streets");
                Map<Long, String> sources = (Map<Long, String>) request.getAttribute("sources");
                Map<Long, String> districts = (Map<Long, String>) request.getAttribute("districts");
                Map<Long, String> buildingTypes = (Map<Long, String>) request.getAttribute("buildingTypes");
            %>
            <input type="hidden" name="id" value="<%= tenant.getId() %>">
            <div class="form-group mb-3">
                <label for="firstName">Ім'я:</label>
                <input type="text" id="firstName" name="firstName" value="<%= tenant.getFirstName() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="middleName">По батькові:</label>
                <input type="text" id="middleName" name="middleName" value="<%= tenant.getMiddleName() %>">
            </div>
            <div class="form-group mb-3">
                <label for="lastName">Прізвище:</label>
                <input type="text" id="lastName" name="lastName" value="<%= tenant.getLastName() %>">
            </div>
            <div class="form-group mb-3">
                <label for="phone">Телефон:</label>
                <input type="text" id="phone" name="phone" value="<%= tenant.getPhone() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" value="<%= tenant.getEmail() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="streetId">Вулиця:</label>
                <select id="streetId" name="streetId" required>
                    <%
                        for (Map.Entry<Long, String> street : streets.entrySet()) {
                            long streetId = street.getKey();
                            String streetName = street.getValue();
                            String selected = streetId == tenant.getStreetId() ? "selected" : "";
                            %>
                    <option value="<%=streetId%>" <%= selected %>><%= streetName %> </option>
                        <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="building">Номер будинку:</label>
                <input type="text" id="building" name="building" value="<%= tenant.getBuildingNumber() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="premise">Номер квартири:</label>
                <input type="text" id="premise" name="premise" value="<%= tenant.getPremiseNumber() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="district">Бажаний район:</label>
                <select id="district" name="district" required>
                    <%
                        for (Map.Entry<Long, String> district : districts.entrySet()) {
                            long districtId = district.getKey();
                            String districtName = district.getValue();
                            String selected = districtId == tenant.getDesiredDistrictId() ? "selected" : "";
                    %>
                    <option value="<%=districtId%>" <%= selected %>><%= districtName %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="objectType">Бажаний тип приміщення:</label>
                <select id="objectType" name="objectType" required>
                    <%
                        for (Map.Entry<Long, String> objectType : buildingTypes.entrySet()) {
                            long buildingTypeId = objectType.getKey();
                            String objectTypeName = objectType.getValue();
                            String selected = buildingTypeId == tenant.getDesiredObjectTypeId() ? "selected" : "";
                    %>
                    <option value="<%=buildingTypeId%>" <%= selected %>><%= objectTypeName %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="source">Джерело залучення:</label>
                <select id="source" name="source" required>
                    <%
                        for (Map.Entry<Long, String> source : sources.entrySet()) {
                            long sourceId = source.getKey();
                            String sourceName = source.getValue();
                            String selected = sourceId == tenant.getAcquisitionSourceId() ? "selected" : "";
                    %>
                    <option value="<%=sourceId%>" <%= selected %>><%= sourceName %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="budget">Бюджет:</label>
                <input type="text" id="budget" name="budget" value="<%= tenant.getBudget() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="area">Бажана площа приміщення:</label>
                <input type="text" id="area" name="area" value="<%= tenant.getDesiredArea() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="acquisitionDate">Дата залучення:</label>
                <input type="date" id="acquisitionDate" name="acquisitionDate" value="<%= tenant.getAcquisitionDate() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Зберегти</button>
        </form>
    </div>
</div>
</body>
</html>
