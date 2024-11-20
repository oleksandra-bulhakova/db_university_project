<%--
  Created by IntelliJ IDEA.
  User: o.bulhakova
  Date: 11/20/2024
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Головна сторінка</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .btn-group-vertical {
      width: 100%;
    }
    .btn {
      font-size: 18px;
      margin-bottom: 15px;
    }
    .container {
      margin-top: 50px;
    }
    h1 {
      text-align: center;
      margin-bottom: 40px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Головне меню</h1>

  <!-- Button group for menu items -->
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="btn-group-vertical w-100">
        <a href="real-estate-objects" class="btn btn-primary btn-lg">Об'єкти нерухомості</a>
        <a href="owners" class="btn btn-secondary btn-lg">Власники</a>
        <a href="realtors" class="btn btn-success btn-lg">Рієлтори</a>
        <a href="tenants" class="btn" style="background-color: #8B4513; color: white;">Орендарі</a>
        <a href="demonstrations" class="btn btn-warning btn-lg">Покази</a>
        <a href="deals" class="btn btn-info btn-lg">Угоди</a>
        <a href="statistics" class="btn btn-dark btn-lg">Статистики</a>
        <a href="reports" class="btn btn-danger btn-lg">Звіти</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>