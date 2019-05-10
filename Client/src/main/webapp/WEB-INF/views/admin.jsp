<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Администратор</title>
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
            <div class="panel-body" style="background-color: white">
                <h3>Список пользователей:</h3>
                <TABLE class="table table-striped table-hover">
                    <TR style="background-color: #2a6496; color: #2a6496">
                        <TH>Логин</TH>
                        <TH>Имя</TH>
                        <TH>Фамилия</TH>
                        <TH>Роль</TH>
                        <TH>Тип регистрации</TH>
                        <TH></TH>
                    </TR>
                    <c:forEach var="tableUser" items="${users}">
                        <c:if test="${sessionScope.get('username') != tableUser.username}">
                            <TR>
                                <TD>${tableUser.username}</TD>
                                <TD>${tableUser.name}</TD>
                                <TD>${tableUser.surname}</TD>
                                <TD>${tableUser.role}</TD>
                                <TD>${tableUser.regType}</TD>
                                <form action="changerole" method="post">
                                    <input type="hidden" name="role" value="${tableUser.role}">
                                <c:choose>
                                    <c:when test="${tableUser.role eq 'user'}">
                                        <TD>
                                            <button name="username" value="${tableUser.username}">Повысить</button>
                                        </TD>
                                    </c:when>
                                    <c:otherwise>
                                        <TD>
                                            <button name="username" value="${tableUser.id}">Понизить</button>
                                        </TD>
                                    </c:otherwise>
                                </c:choose>
                                </form>
                            </TR>
                        </c:if>
                    </c:forEach>
                </TABLE>
                <br>
                <br>
                <form:form method="post" action="logout">
                    <button type="submit" name="logout" class="btn">Выйти</button>
                </form:form>
            </div>
        </div>
    </div>
</div>


<footer>
    <div class="container">
        <div class="col-md-12 text-right"><h5>Copyright © 2019 Ann.Vasilyeva</h5></div>
    </div>
</footer>

</body>
</html>