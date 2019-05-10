<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Список статей</title>
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
                    <c:choose>
                        <c:when test="${sessionScope.get('role') eq null}">
                            <form:form method="GET" action="login">
                                <button style="margin-top: 15px; background-color: Transparent;border: none;outline:none">
                                    <img src="<c:url value="/resources/img/Plus.png"/>" class="login">
                                </button>
                            </form:form>
                        </c:when>
                        <c:otherwise>
                            <form:form method="POST" action="logout">
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

        <div class="col-md-12" id="content">
            <div class="panel">
                <div class="panel-heading" style="background-color:#111;color:#fff;height: 40px">
                </div>
                <div class="panel-body">
                    <h3>Список статей: </h3>
                    <form:form action="addedit" method="get">
                        <button class="btn-block" type="submit" name="id" value="0">Добавить статью</button>
                    </form:form>
                    <TABLE class="table table-striped table-hover">
                        <TR style="background-color: #2a6496; color: #2a6496">
                            <TH>НАЗВАНИЕ</TH>
                            <TH>КРАТКОЕ СОДЕРЖАНИЕ</TH>
                            <TH></TH>
                            <c:if test="${sessionScope.get('role') ne null}">
                                <TH></TH>
                                <c:if test="${sessionScope.get('role') eq 'admin'}">
                                    <TH></TH>
                                </c:if>
                            </c:if>
                        </TR>
                        <c:forEach var="article" items="${articles}">
                            <TR>
                                <TD>${article.topic}</TD>
                                <TD>${article.summary}</TD>
                                <form:form action="article" method="GET" id="show">
                                    <TD>
                                        <button style="background-color: Transparent; border: none;outline:none" name="id" value="${article.id}">
                                            <img src="<c:url value="/resources/img/Documents.png"/>" class="login">
                                        </button>
                                    </TD>
                                </form:form>
                                <c:if test="${sessionScope.get('role') ne null}">
                                    <td>
                                        <form:form action="addedit" method="GET">
                                            <button style="background-color: Transparent; border: none;outline:none" name="id" value="${article.id}">
                                                <img src="<c:url value="/resources/img/Pencil.png"/>" class="login">
                                            </button>
                                        </form:form>
                                    </td>
                                    <c:if test="${sessionScope.get('role') eq 'admin'}">
                                        <td>
                                            <form:form action="article" method="POST">
                                                <button style="background-color: Transparent; border: none;outline:none" name="id" value="${article.id}">
                                                    <img src="<c:url value="/resources/img/Trash.png"/>" class="login">
                                                </button>
                                            </form:form>
                                        </td>
                                    </c:if>
                                </c:if>
                            </TR>
                        </c:forEach>
                    </TABLE>
                    <br>
                    <br>
                    <form:form action="addedit" method="get">
                        <button class="btn-block" type="submit" name="id" value="0">Добавить статью</button>
                    </form:form>
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