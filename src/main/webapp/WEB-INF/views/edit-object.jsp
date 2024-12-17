<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.commercial_real_estate.model.entities.RealEstateObject" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Редагувати об'єкт нерухомості</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/real-estate-objects" class="btn btn-secondary mb-3">Назад до списку</a>
</div>
<div class="container mt-4">
  <h1 class="container">Редагувати об'єкт нерухомості</h1>
  <div class="container mt-3">
    <form method="post" action="edit-object">
      <%
        RealEstateObject object = (RealEstateObject) request.getAttribute("object");
        Map<Long, String> streets = (Map<Long, String>) request.getAttribute("streets");
        Map<Long, String> buildingTypes = (Map<Long, String>) request.getAttribute("buildingTypes");
        Map<Long, String> owners = (Map<Long, String>) request.getAttribute("owners");
      %>
      <input type="hidden" name="id" value="<%= object.getId() %>">
      <div class="form-group mb-3">
        <label for="streetId">Вулиця:</label>
        <select id="streetId" name="streetId" required>
          <%
            for (Map.Entry<Long, String> street : streets.entrySet()) {
              long streetId = street.getKey();
              String streetName = street.getValue();
              String selected = streetId == object.getStreetId() ? "selected" : "";
          %>
          <option value="<%=streetId%>" <%= selected %>><%= streetName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="building">Номер будинку:</label>
        <input type="text" id="building" name="building" value="<%= object.getBuildingNumber() %>" required>
      </div>
      <div class="form-group mb-3">
        <label for="premise">Номер приміщення:</label>
        <input type="text" id="premise" name="premise" value="<%= object.getPremiseNumber() %>" required>
      </div>
      <div class="form-group mb-3">
        <label for="owner">Власник:</label>
        <select id="owner" name="owner" required>
          <%
            for (Map.Entry<Long, String> owner : owners.entrySet()) {
              long ownerId = owner.getKey();
              String name = owner.getValue();
              String selected = ownerId == object.getOwnerId() ? "selected" : "";
          %>
          <option value="<%=ownerId%>" <%= selected %>><%= name %> </option>
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
              String selected = buildingTypeId == object.getObjectTypeId() ? "selected" : "";
          %>
          <option value="<%=buildingTypeId%>" <%= selected %>><%= objectTypeName %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="area">Площа приміщення, м²:</label>
        <input type="text" id="area" name="area" value="<%= object.getArea() %>" required>
      </div>
      <div class="form-group mb-3">
        <label for="floor">Поверх:</label>
        <input type="text" id="floor" name="floor" value="<%= object.getFloor() %>" required>
      </div>
      <div class="form-group mb-3">
        <label for="renovation">Наявність ремонту:</label>
        <select id="renovation" name="renovation" required>
          <%
            List<Boolean> renovations = List.of(true, false);
            for (Boolean renovation : renovations) {
              String choice = renovation ? "Є" : "НЕМАЄ";
              String selected = (object.isRenovation() == renovation) ? "selected" : "";
          %>
          <option value="<%=renovation%>" <%= selected %>><%= choice %> </option>
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
              String selected = (object.isFurniture() == furniture) ? "selected" : "";
          %>
          <option value="<%=furniture%>" <%= selected %>><%= choice %> </option>
          <%}%>
        </select>
      </div>
      <div class="form-group mb-3">
        <label for="price">Ціна оренди:</label>
        <input type="text" id="price" name="price" value="<%= object.getPrice() %>" required>
      </div>
      <button type="submit" class="btn btn-primary">Зберегти</button>
    </form>
  </div>
</div>

</body>
</html>
