<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.commercial_real_estate.model.RealEstateObject" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Створити об'єкт нерухомості</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="/real-estate-objects" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
    <h1 class="container">Створити об'єкт нерухомості</h1>
    <div class="container mt-3">
        <form method="post" action="create">
            <%
                RealEstateObject object = (RealEstateObject) request.getAttribute("realEstateObject");
                Map<Long, String> streets = (Map<Long, String>) request.getAttribute("streets");
                Map<Long, String> buildingTypes = (Map<Long, String>) request.getAttribute("buildingTypes");
                Map<Long, String> owners = (Map<Long, String>) request.getAttribute("owners");
            %>
            <div class="form-group mb-3">
                <label for="streetId">Вулиця:</label>
                <select id="streetId" name="streetId" required>
                    <%
                        for (Map.Entry<Long, String> street : streets.entrySet()) {
                            long streetId = street.getKey();
                            String streetName = street.getValue();
                    %>
                    <option value="<%=streetId%>"><%= streetName %></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="building">Номер будинку:</label>
                <input type="text" id="building" name="building" required>
            </div>
            <div class="form-group mb-3">
                <label for="premise">Номер приміщення:</label>
                <input type="text" id="premise" name="premise" required>
            </div>
            <div class="form-group mb-3">
                <label for="owner">Власник:</label>
                <select id="owner" name="owner" required>
                    <%
                        for (Map.Entry<Long, String> owner : owners.entrySet()) {
                            long ownerId = owner.getKey();
                            String name = owner.getValue();
                    %>
                    <option value="<%=ownerId%>"> <%= name %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="objectType">Тип приміщення:</label>
                <select id="objectType" name="objectType" required>
                    <%
                        for (Map.Entry<Long, String> objectType : buildingTypes.entrySet()) {
                            long buildingTypeId = objectType.getKey();
                            String objectTypeName = objectType.getValue();
                    %>
                    <option value="<%=buildingTypeId%>"><%= objectTypeName %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="area">Площа приміщення, м²:</label>
                <input type="text" id="area" name="area" required>
            </div>
            <div class="form-group mb-3">
                <label for="floor">Поверх:</label>
                <input type="text" id="floor" name="floor" required>
            </div>
            <div class="form-group mb-3">
                <label for="renovation">Наявність ремонту:</label>
                <select id="renovation" name="renovation" required>
                    <%
                        List<Boolean> renovations = List.of(true, false);
                        for (Boolean renovation : renovations) {
                            String choice = renovation ? "Є" : "НЕМАЄ";
                    %>
                    <option value="<%=renovation%>"><%= choice %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="furniture">Наявність меблів:</label>
                <select id="furniture" name="furniture" required>
                    <%
                        List<Boolean> furnitureOptions = List.of(true, false);
                        for (Boolean furniture : furnitureOptions) {
                            String choice = furniture ? "Є" : "НЕМАЄ";
                    %>
                    <option value="<%=furniture%>"><%= choice %> </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="price">Ціна оренди:</label>
                <input type="text" id="price" name="price" required>
            </div>
            <button type="submit" class="btn btn-primary">Зберегти</button>
        </form>
    </div>
</div>

</body>
</html>
