<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
          integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
          integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
          integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <html>
<body>
<div class="container pt-3">
  <div class="row col-md-6 offset-md-3">
    <div class="card" style="width: 100%">
      <div class="card-header">
        <h6>Редактировать инцидент!</h6>
      </div>
      <div class="card-body">
        <form action="<c:url value='/save?id=${accident.id}'/>" method="POST">
          <div class="form-group">
            <label for="name">Название инцидента</label>
            <input type="text" required class="form-control" name="name" title="Enter name..." id="name" value="${accident.name}">
          </div>
          <div class="form-group" hidden>
            <label for="text">Описание инцидента</label>
            <input type="text"  class="form-control" name="text" title="Enter text..." id="text" value="${accident.text}">
          </div>
          <div class="form-group" hidden>
            <label for="address">Адрес инцидента</label>
            <input type="text" hidden class="form-control" name="address" title="Enter address..." id="address" value="${accident.address}">
          </div>
          <div class="form-group" hidden>
            <input type="text" hidden class="form-control" name="type.id" value="${accident.type.id}">
          </div>
          <div class="form-group" hidden>
            <select name="rIds" multiple>
              <c:forEach items="${accident.rules}" var="rule">
                <option selected value="${rule.id}">${rule.name}</option>
              </c:forEach>
            </select>
          </div>
          <button name="submit" type="submit" value="Сохранить" class="btn btn-primary">Сохранить</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
