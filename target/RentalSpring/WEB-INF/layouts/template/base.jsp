<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><tiles:insertAttribute name="titolo"/> </title>

    <!-- CSS only -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!--icone bootstrap-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Rental Cars</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <sec:authorize access="hasRole('ADMIN')">
                <a class="nav-link" href="listCustomer">Home <span class="sr-only">(current)</span></a>
                </sec:authorize>
                <sec:authorize access="hasRole('CUSTOMER')">
                    <a class="nav-link" href="listReservations">Home <span class="sr-only">(current)</span></a>
                </sec:authorize>
                        </li>
            <li class="nav-item ">
                <a class="nav-link" href="listCar">Parco Auto <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="myProfile">Il mio profilo<span class="sr-only">(current)</span></a>
            </li>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item ">
                    <a class="nav-link" href="logout"><u>Logout</u></a>
                </li>
            </sec:authorize>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="searchUsers" method="GET">
            <input class="form-control mr-sm-2" type="search" name="filter" placeholder="${ToSearch}" ${ToSearch} aria-label="Search">
            <button class="btn btn-success my-2 my-sm-0" type="submit" ${ToSearch}>Cerca</button>
        </form>
    </div>
</nav>


<tiles:insertAttribute name="content"/>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
