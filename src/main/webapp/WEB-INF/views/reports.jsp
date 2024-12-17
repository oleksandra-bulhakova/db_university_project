<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Генерація звіту</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            padding-bottom: 20px;
        }

        .container {
            width: 90%;
            margin: 0 auto;
            padding: 0px;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        .item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        h1, h3 {
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
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $("form").on("submit", function (event) {
                event.preventDefault();

                var form = $(this);
                var reportType = form.find('input[name="reportType"]').val();
                var startDate = reportType === "deals" ? form.find('input[name$="startDate"]').val() : form.find('input[name$="startDate1"]').val();
                var endDate = reportType === "deals" ? form.find('input[name$="endDate"]').val() : form.find('input[name$="endDate1"]').val();

                $.ajax({
                    url: "/reports",
                    type: "POST",
                    data: {
                        reportType: reportType,
                        startDate: startDate,
                        endDate: endDate
                    },
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function (response) {

                        var fileName = reportType === "deals" ? "deals_report.pdf" : "demand_report.pdf";

                        $('#message').html('<div class="alert alert-success">Звіт успішно згенеровано!</div>');
                        var blob = new Blob([response], {type: 'application/pdf'});
                        var link = document.createElement('a');
                        link.href = URL.createObjectURL(blob);
                        link.download = fileName;
                        link.click();
                    },
                    error: function () {
                        $('#message').html('<div class="alert alert-danger">Сталася помилка під час генерації звіту.</div>');
                    }
                });
            });
        });
    </script>
</head>
<body>
<div id="message"></div>
<div class="container mt-3">
    <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
</div>
<h1>Звіти</h1>
<div class="container">
    <div class="grid">
        <div class="item">
            <h3>Звіт про скасовані угоди</h3>
            <form class="form" action="reports" method="post">
                <input type="hidden" name="reportType" value="deals">
                <label for="startDate"></label>
                <input type="date" id="startDate" name="startDate" required>
                <label for="endDate"></label>
                <input type="date" id="endDate" name="endDate" required>
                <button class="submit" type="submit">Згенерувати звіт</button>
            </form>
        </div>
        <div class="item">
            <h3>Звіт про запити орендарів</h3>
            <form class="form" action="reports" method="post">
                <input type="hidden" name="reportType" value="demand">
                <label for="startDate1"></label>
                <input type="date" id="startDate1" name="startDate1" required>
                <label for="endDate1"></label>
                <input type="date" id="endDate1" name="endDate1" required>
                <button class="submit" type="submit">Згенерувати звіт</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
