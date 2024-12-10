<%@ page import="org.commercial_real_estate.model.Realtor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Рієлтори</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        table {
            width: 100%;
        }

        .table-responsive {
            overflow-x: auto;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        input[type="text"] {
            width: 100%;
        }

        .button-container {
            display: flex;
            gap: 10px;
            justify-content: flex-start;
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

    </style>
</head>
<body>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
    <a href="realtors/create-realtor" class="btn btn-primary btn-wide mb-3 float-right">Додати нового рієлтора</a>
</div>
<h1 class="container">Всі рієлтори</h1>
<div class="container mt-3">
    <div class="alert alert-info" role="alert">
        <%List<Realtor> obj = (List<Realtor>) request.getAttribute("realtors");%>
        <strong>Всього рієлторів: </strong><%= (obj != null) ? obj.size() : 0 %>
        <br>
        <strong>З них активних зараз: </strong>
        <%= (obj != null) ? obj.stream().filter(realtor -> realtor.getWorkingStatus().equals("АКТИВНИЙ")).toList().size() : 0 %>
    </div>
</div>
<div class="container mt-3">
    <form action="/realtors" method="get" class="mb-3">
        <input type="text"
               name="search"
               value="<%= request.getAttribute("searchQuery") != null ? request.getAttribute("searchQuery") : "" %>"
               placeholder="Пошук за ПІБ" class="form-control"/>
        <div class="button-container">
            <button type="submit" class="btn btn-success">Пошук</button>
            <a href="/realtors" class="btn btn-info">Скинути</a>
        </div>
    </form>
    <form action="/realtors" method="get" class="mb-3">
        <select name="specialization" class="form-control" onchange="this.form.submit()">
            <option value="">Фільтрувати за спеціалізацією</option>
            <option value="Офісна нерухомість"<%= "Офісна нерухомість".equals(request.getAttribute("specialization")) ? "selected" : "" %>>Офісна нерухомість</option>
            <option value="Торгова нерухомість"<%= "Торгова нерухомість".equals(request.getAttribute("specialization")) ? "selected" : "" %>>Торгова нерухомість</option>
            <option value="Складська нерухомість"<%= "Складська нерухомість".equals(request.getAttribute("specialization")) ? "selected" : "" %>>Складська нерухомість</option>
            <option value="Промислова нерухомість"<%= "Промислова нерухомість".equals(request.getAttribute("specialization")) ? "selected" : "" %>>Промислова нерухомість</option>
            <option value="Готельна нерухомість"<%= "Готельна нерухомість".equals(request.getAttribute("specialization")) ? "selected" : "" %>>Готельна нерухомість</option>
        </select>
    </form>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark sticky-top">
            <tr>
                <th>
                    <span>ПІБ</span>
                    <a
                            href="?sortDirection=asc<%= request.getAttribute("searchQuery") != null ? "&search=" + request.getAttribute("searchQuery") : "" %>"
                            title="asc"
                            style="margin-left: 8px; text-decoration: none; font-size: 16px;">&#9650;</a>
                    <a
                            href="?sortDirection=desc<%= request.getAttribute("searchQuery") != null ? "&search=" + request.getAttribute("searchQuery") : "" %>"
                            title="desc"
                            style="margin-left: 4px; text-decoration: none; font-size: 16px;">&#9660;</a>
                </th>
                <th>Адреса</th>
                <th>Контакти</th>
                <th>Рівень</th>
                <th>Спеціалізація</th>
                <th>Статус роботи</th>
                <th>Дата виходу</th>
                <th>Дії</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Realtor> realtors = (List<Realtor>) request.getAttribute("realtors");
                if (realtors != null && !realtors.isEmpty()) {
                    for (Realtor realtor : realtors) {
            %>
            <tr>
                <td><%= realtor.getFullName() %>
                </td>
                <td><%= realtor.getAddress() %>
                </td>
                <td><%= "тел.: " + realtor.getPhone() + ", email: " + realtor.getEmail()%>
                </td>
                <td><%= realtor.getLevel() %>
                </td>
                <td><%= realtor.getSpecialization() %>
                </td>
                <td><%= realtor.getWorkingStatus() %>
                </td>
                <td><%= realtor.getStartDate() %>
                </td>
                <td>
                    <a href="edit-realtor?id=<%= realtor.getId() %>" class="btn btn-warning btn-sm">Редагувати</a>
                    <br>
                    <a href="realtors/delete?id=<%= realtor.getId() %>" class="btn btn-danger btn-sm mt-1">Видалити</a>
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