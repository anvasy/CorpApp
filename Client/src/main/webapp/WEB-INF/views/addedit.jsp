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
                            <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                                <img src="<c:url value="/resources/img/Plus.png"/>" class="login">
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                                <img src="<c:url value="/resources/img/Minus.png"/>" class="login">
                            </button>
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
                <div class="panel-heading" style="background-color:#111;color:#fff;height: 40px"></div>

                <div class="panel-body">
                    <form:form method="post" action="addedit" modelAttribute="article">
                        <h4>Тема: </h4>
                        <form:input  path = "topic" required="required" class="form-control" pattern="[A-Za-z0-9- ]+" value="${article.topic}"/>

                        <h4>Краткое содержание:</h4>
                        <form:textarea  path = "summary" required="required" class="form-control" value="${article.summary}" cssStyle="resize: none; height: 15%"/>

                        <h4>Текст статьи:</h4>
                        <form:textarea  path = "content" required="required" class="form-control" value="${article.content}" cssStyle="resize: none; height: 50%"/>
                        <br>
                        <br>
                        <button class="btn-block" type="submit" name="id" value="${article.id}">Сохранить</button>
                    </form:form>
                    <hr>
                    <form:form action="article" method="get">
                        <button class="btn-block" type="submit" value="${article.id}" name="id">Отмена</button>
                    </form:form>
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
