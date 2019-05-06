<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Регистрация</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-static">
    <div class="container">
        <div class="navbar-header"></div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-right navbar-nav">
                <li>
                    <form:form method="GET" action="home">
                        <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                            <img src="<c:url value="/resources/img/Home.png"/>" class="login">
                        </button>
                    </form:form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="no-gutter row">
        <div style="height: 40px;"></div>
        <div class="col-md-12" id="content">
            <div class="panel-heading" style="background-color:#111;color:#fff;height: 40px">
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-6" style="height: 55%; background-color: white;
                margin-top: 20px;">
                <form:form method="POST" action="register" modelAttribute="user">
                    <br>
                    <h4 style="margin-left: 5%">Имя пользователя: </h4>
                    <c:if test="${exists == true}">
                        <h6 style="margin-left: 5%; background: red; color: white; width: 25%">такое имя уже существует </h6>
                    </c:if>
                    <form:input  path = "username" required="required" class="form-control"
                                 cssStyle="width: 90%; margin-left: 5%" pattern="[a-z0-9-]+"/>
                    <br>
                    <h4 style="margin-left: 5%">Пароль:</h4>
                    <form:password path = "password" class="form-control"
                                   cssStyle="width: 90%; margin-left: 5%" required="required" pattern="[A-Za-z0-9]+"/>
                    <br>
                    <br>
                    <input type="submit" class="button" name="register" value="Зарегистрироваться" style="height: 50px; width: 90%; font-size: 20px;
                        margin-left: 5%; background-color: #5BDD5B; color: white;"/>
                </form:form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

<footer style="position: fixed; left: 0; bottom: 0; width: 100%">
    <div class="container">
        <div class="col-md-12 text-right"><h5>Copyright © 2018 An.Vasy</h5></div>
    </div>
</footer>

</body>
</html>