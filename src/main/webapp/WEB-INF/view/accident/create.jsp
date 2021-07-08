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
    <div class="col" style="margin-bottom: 35px">
        <ul class="nav float-right">
            <c:if test="${user != null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDDMenu" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.username}
                    </a>
                    <div class="dropdown-menu text-center" aria-labelledby="navbarDDMenu">
                        <a class="dropdown-item" href="<c:url value='/'/>">Главная</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Выйти</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row col-md-6 offset-md-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h4 style="color: cadetblue">Добавить инцидент!</h4>
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method="POST">
                    <div class="form-group">
                        <label for="name">Название инцидента</label>
                        <input type="text" required class="form-control" name="name" title="Enter name..." id="name">
                    </div>
                    <div class="form-group">
                        <label for="text">Описание инцидента</label>
                        <input type="text" required class="form-control" name="text" title="Enter text..." id="text">
                    </div>
                    <div class="form-group">
                        <label for="address">Адрес инцидента</label>
                        <input type="text" required class="form-control" name="address" title="Enter address..." id="address">
                    </div>
                    <div class="form-group">
                        <div class="row py-2">
                            <div class="col">
                                <select class="custom-select mr-sm-2" name="type.id" required>
                                    <option selected="selected">Не выбрано...</option>
                                    <c:forEach var="type" items="${types}">
                                        <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <select class="custom-select mr-sm-2" name="rIds" multiple size="3">
                                    <option selected="selected">Не выбрано...</option>
                                    <c:forEach items="${rules}" var="rule">
                                        <option value="${rule.id}">${rule.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button name="submit" type="submit" value="Сохранить" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
