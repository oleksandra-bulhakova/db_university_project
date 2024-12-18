<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Головна сторінка</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
  <style>
    .menu-card {
      margin-bottom: 20px;
    }
    .menu-container {
      margin-top: 50px;
    }
    .menu-title {
      text-align: center;
      margin-bottom: 30px;
      color: #343a40;
    }
    .menu-button {
      font-size: 18px;
    }
    .menu-icon {
      margin-right: 10px;
    }
  </style>
</head>
<body>
<div class="container menu-container">
  <h1 class="menu-title">Головне меню</h1>

  <div class="row">

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="real-estate-objects" class="btn btn-primary btn-lg menu-button w-100">
            <i class="bi bi-building menu-icon"></i>Об'єкти нерухомості
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="owners" class="btn btn-primary btn-lg menu-button w-100">
            <i class="bi bi-person-badge menu-icon"></i>Власники
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="realtors" class="btn btn-primary btn-lg menu-button w-100">
            <i class="bi bi-person-workspace menu-icon"></i>Рієлтори
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="tenants" class="btn btn-primary btn-lg menu-button w-100">
            <i class="bi bi-people menu-icon"></i>Орендарі
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="demonstrations" class="btn btn-info btn-lg menu-button w-100">
            <i class="bi bi-eye-fill menu-icon"></i>Покази
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="deals" class="btn btn-info btn-lg menu-button w-100">
            <i class="bi bi-briefcase menu-icon"></i>Угоди
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="stats" class="btn btn-warning btn-lg menu-button w-100">
            <i class="bi bi-bar-chart menu-icon"></i>Статистики
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="reports" class="btn btn-warning btn-lg menu-button w-100">
            <i class="bi bi-file-earmark-text menu-icon"></i>Звіти
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="send" class="btn btn-warning btn-lg menu-button w-100" style="background-color: #ff9f00;">
            <i class="bi bi-gear menu-icon"></i>Автоматизація
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6 menu-card">
      <div class="card">
        <div class="card-body text-center">
          <a href="execute-query" class="btn btn-warning btn-lg menu-button w-100" style="background-color: #ff9f00;">
            <i class="bi bi-lightning menu-icon"></i>SQL запит
          </a>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
</html>