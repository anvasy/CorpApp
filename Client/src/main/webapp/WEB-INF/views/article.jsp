<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Статья</title>
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
                <li>
                    <c:choose>
                        <c:when test="${sessionScope.get('role') eq null}">
                            <form:form method="GET" action="login">
                                <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                                    <img src="<c:url value="/resources/img/Plus.png"/>" class="login">
                                </button>
                            </form:form>
                        </c:when>
                        <c:otherwise>
                            <form:form method="POST" action="admin">
                                <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                                    <img src="<c:url value="/resources/img/Minus.png"/>" class="login">
                                </button>
                            </form:form>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="no-gutter row">
        <div style="height: 40px;"></div>
        <div class="col-md-12" id="content">
            <div class="panel">
                <div class="panel-heading" style="background-color:#111;color:#fff;height: 40px">
                    <div class="col-sm-6" style="text-align: center;">
                        <c:out value='${article.topic}'/>
                    </div>
                </div>

                <div class="panel-body">
                    <h3>Оценка: ${article.rate}</h3>
                    <form action="bonus" method="post">
                        <select style="height: 50px; margin-left: 20px" name="rated">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                        <button name="rated" style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                            <img src="<c:url value="/resources/img/Star.png"/>" class="login">
                        </button>
                    </form>
                    <hr>
                    <p>${article.content}</p>
                    <hr>
                    <c:if test="${sessionScope.get('role') ne null}">
                        <form:form action="addedit" method="get">
                            <button class="btn-block" type="submit" name="id" value="${article.id}">Изменить</button>
                        </form:form>
                        <c:if test="${sessionScope.get('role') eq 'admin'}">
                            <form:form action="article" method="post">
                                <button class="btn-block" type="submit" name="id" value="${article.id}">Удалить</button>
                            </form:form>
                        </c:if>
                    </c:if>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="col-md-12 text-right"><h5>Copyright © 2018 An.Vasy</h5></div>
    </div>
</footer>

</body>
</html>
