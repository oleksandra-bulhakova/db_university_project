<%@ page import="org.commercial_real_estate.model.entities.Tenant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Орендарі</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        table {
            width: 100%;
        }

        .table-responsive {
            overflow-x: auto;
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
        .button-container {
            display: flex;
            gap: 10px;
            justify-content: flex-start;
        }
        form {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            margin-top: 10px;
            margin-left: 0;
            padding-left: 0;
            box-sizing: border-box;
            gap: 15px;
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
    </style>
</head>
<body>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
    <a href="tenants/create-tenant" class="btn btn-primary btn-wide mb-3 float-right">Додати нового орендаря</a>
</div>
<h1 class="container">Всі орендарі</h1>
<div class="container mt-3">

    <form action="/tenants" method="get" class="mb-3">
        <input type="text"
               name="search"
               value="<%= request.getAttribute("searchQuery") != null ? request.getAttribute("searchQuery") : "" %>"
               placeholder="Пошук за ПІБ" class="form-control"/>
        <div class="button-container">
            <button type="submit" class="btn btn-success">Пошук</button>
            <a href="/tenants" class="btn btn-info">Скинути</a>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark sticky-top">
            <tr>
                <th>ПІБ</th>
                <th>Адреса</th>
                <th>Телефон</th>
                <th>Email</th>
                <th>Дата залучення</th>
                <th>Джерело залучення</th>
                <th>Запит</th>
                <th>Дії</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Tenant> tenants = (List<Tenant>) request.getAttribute("tenants");
                if (tenants != null && !tenants.isEmpty()) {
                    for (Tenant tenant : tenants) {
            %>
            <tr>
                <td><%= tenant.getFirstName() + " " + tenant.getMiddleName() + " " + tenant.getLastName() %>
                </td>
                <td><%= tenant.getStreetName() + ", " + tenant.getBuildingNumber() + ", кв. " + tenant.getPremiseNumber() %>
                </td>
                <td><%= tenant.getPhone() %>
                </td>
                <td><%= tenant.getEmail() %>
                </td>
                <td><%= tenant.getAcquisitionDate() %>
                </td>
                <td><%= tenant.getAcquisitionSourceName() %>
                </td>
                <td><%= tenant.getDesiredDistrictName() + " район, тип приміщ. - " + tenant.getDesiredObjectTypeName() + ", " + tenant.getDesiredArea() + " м²." + " Бюджет " + tenant.getBudget() + " грн./міс." %>
                </td>

                <td>
                    <a href="edit-tenant?id=<%= tenant.getId() %>" class="btn btn-warning btn-sm">Редагувати</a>
                    <br>
                    <a href="tenants/delete?id=<%= tenant.getId() %>" class="btn btn-danger btn-sm mt-1">Видалити</a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="8">Немає даних</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
