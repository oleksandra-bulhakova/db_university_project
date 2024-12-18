<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Виконання SQL запиту</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
  <a href="/" class="btn btn-secondary btn-wide mb-3">На головну</a>
</div>
<div class="container mt-3">
  <h2>Введіть ваш SQL запит</h2>
  <form action="execute-query" method="post">
    <div class="form-group">
      <label for="query">SQL Запит:</label>
      <textarea id="query" name="query" class="form-control" rows="5" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Виконати запит</button>
  </form>

  <hr>
  <%
    List<List<String>> result = (List<List<String>>) request.getAttribute("result");
    String error = (String) request.getAttribute("error");

    if (result != null && !result.isEmpty()) {
  %>
  <div class="alert alert-success mt-3">
    <h5>Результат запиту:</h5>
    <table class="table table-bordered">
      <tbody>
      <%
        for (List<String> row : result) {
      %>
      <tr>
        <%
          for (String cell : row) {
        %>
        <td><%= cell %></td>
        <%
          }
        %>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>
  <%
    }
    if (error != null && !error.isEmpty()) {
  %>
  <div class="alert alert-danger mt-3">
    <h5>Помилка:</h5>
    <p><%= error %></p>
  </div>
  <%
    }
  %>
</div>
</body>
</html>