<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/25/2024
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.commercial_real_estate.model.Demonstration" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Редагувати показ</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"></head>
<body>
<div class="container mt-3">
    <a href="/demonstrations" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
    <h1 class="container">Редагувати покази</h1>
    <div class="container mt-3">
        <form method="post" action="edit-demo">
            <%
                Demonstration demonstration = (Demonstration) request.getAttribute("demonstration");
                Map<Long, String> objects = (Map<Long, String>) request.getAttribute("objects");
                Map<Long, String> statuses = (Map<Long, String>) request.getAttribute("statuses");
                Map<Long, String> tenants = (Map<Long, String>) request.getAttribute("tenants");
                Map<Long, String> realtors = (Map<Long, String>) request.getAttribute("realtors");
            %>
            <input type="hidden" name="id" value="<%= demonstration.getId() %>">
            <div class="form-group mb-3">
                <label for="object">Об'єкт:</label>
                <select id="object" name="object" required>
                    <%
                        for (Map.Entry<Long, String> obj : objects.entrySet()) {
                            long objectId = obj.getKey();
                            String objectName = obj.getValue();
                            String selected = objectId == demonstration.getObjectId() ? "selected" : "";
                    %>
                    <option value="<%=objectId%>" <%= selected %>><%= objectName %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="demoDate">Дата показу:</label>
                <input type="date" id="demoDate" name="demoDate" value="<%= demonstration.getDate() %>" required>
            </div>
            <div class="form-group mb-3">
                <label for="status">Статус показу:</label>
                <select id="status" name="status" required>
                    <%
                        for (Map.Entry<Long, String> stat : statuses.entrySet()) {
                            long statusId = stat.getKey();
                            String statusName = stat.getValue();
                            String selected = statusId == demonstration.getDemoStatusId() ? "selected" : "";
                    %>
                    <option value="<%=statusId%>" <%= selected %>><%= statusName %> </option>
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
                            String selected = tenantId == demonstration.getTenantId() ? "selected" : "";
                    %>
                    <option value="<%=tenantId%>" <%= selected %>><%= tenantName %> </option>
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
                            String selected = realtorId == demonstration.getTenantId() ? "selected" : "";
                    %>
                    <option value="<%=realtorId%>" <%= selected %>><%= realtorName %> </option>
                    <%}%>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Зберегти</button>
        </form>
    </div>
</div>
</body>
</html>
