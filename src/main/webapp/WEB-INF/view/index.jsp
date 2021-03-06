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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
    <title>Accident</title>
</head>
<body>
<div class="container pt-3">
    <div class="col">
        <ul class="nav float-right">
            <c:if test="${user != null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDDMenu" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.username}
                    </a>
                    <div class="dropdown-menu text-center" aria-labelledby="navbarDDMenu">
                        <a class="dropdown-item" href="<c:url value='/create'/>">Добавить инцидент</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Выйти</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
    <h2 style="color: cadetblue">Все инциденты!</h2>
    <div class="card" style="width: 100%; margin-top: 25px">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>№</th>
                <th>Нарушение</th>
                <th>Тип</th>
                <th>Описание</th>
                <th>КоАП РФ</th>
                <th>Адрес нарушения</th>
                <th><i class="fa fa-pencil-square-o" title="Редактировать"></i></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accidents}" var="accident">
                <tr>
                    <td><c:out value="${accident.id}"/></td>
                    <td><c:out value="${accident.name}"/></td>
                    <td><c:out value="${accident.type.name}"/></td>
                    <td><c:out value="${accident.text}"/></td>
                    <td>
                <c:forEach items="${accident.rules}" var="rule">
                    <p><c:out value="${rule.name}"/></p>
                </c:forEach>
                    </td>
                    <td><c:out value="${accident.address}"/></td>
                    <td>
                        <a href="<c:url value='/update?id=${accident.id}'/>">
                            <i class="fa fa-pencil-square-o" title="Редактировать инцидент..."></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
