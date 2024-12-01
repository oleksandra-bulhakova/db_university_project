<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Помилка видалення</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary mb-3">На головну</a>
    <%String url = (String) request.getAttribute("url");%>
    <a href="<%=url%>" class="btn btn-info mb-3 float-right">Назад до списку</a>
</div>

<div class="container mt-3">
    <div class="alert alert-danger" role="alert">
        <%String message = (String) request.getAttribute("errorMessage");%>
        <h4 class="alert-heading">Помилка видалення!</h4>
        <p><%=message%></p>
    </div>
</div>
</body>
</html>