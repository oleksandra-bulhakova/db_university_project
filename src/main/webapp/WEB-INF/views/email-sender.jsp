<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            width: 90%;
            margin: 0 auto;
            padding: 0px;
            place-items: center;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        h1, h2, h3 {
            text-align: center;
            color: #333;
        }

        .submit {
            color: #333333;
            background-color: #fff9c4;
        }

        .form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .form input, .form button {
            margin: 0 10px;
            padding: 10px;
            font-size: 14px;
        }

        .form button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .alert-custom {
            max-width: 500px;
            margin: 20px auto;
            padding: 15px;
            text-align: center;
            border-radius: 8px;
            font-weight: bold;
        }

        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .alert-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

    </style>
</head>
<body>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
</div>
<div class="container mt-3">

    <h2>Щотижневий звіт на e-mail</h2>
    <div>
        <div>
            <div>
                Введіть свій email, і на нього ви отримуватимете звіт за попередні 7 днів у PDF форматі по понеділках, о
                08:00
            </div>
        </div>
        <form class="form" action="send" method="post">
            <input type="email" id="email" name="email" required>
            <button class="submit" type="submit">Запланувати</button>
        </form>
    </div>
</div>
<%
    String status = request.getParameter("status");
    if ("success".equals(status)) {
%>
<div class="alert-custom alert-success">
    <p class="success">Відправлення звітів на вказаний email успішно заплановано!</p>
</div>
<%
} else if ("error".equals(status)) {
%>
<div class="alert-custom alert-error">
    <p class="error">Виникла помилка. Спробуйте ще раз.</p>
</div>
<%
    }
%>
</body>
</html>

