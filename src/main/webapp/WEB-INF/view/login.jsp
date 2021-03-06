<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <center>
        <div class="col-6">
            <h1>Login Rental Cars</h1>
            <c:url var="loginUrl" value="/" />
            <form action="${loginUrl}" method="POST">

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>EMAIL O PASSWORD ERRATI</p>
                    </div>
                </c:if>

                <c:if test="${param.forbidden != null}">
                    <div class="alert alert-danger">
                        <p>Non hai i permessi per accedere!</p>
                    </div>
                </c:if>

                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>Logout eseguito con successo!</p>
                    </div>
                </c:if>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" class="fadeIn form-control"  placeholder="email">
                <label for="password">Password</label>
                <input type="text" id="password" name="password" class="fadeIn form-control" placeholder="password">
                <br>
                <input type="hidden" name="${_csfr.parameterName}" value="${_csfr.token}" />
                <input type="submit" class="fadeIn btn btn-success" value="Log In">
            </form>
        </div>
    </center>


</div>
</body>
</html>
