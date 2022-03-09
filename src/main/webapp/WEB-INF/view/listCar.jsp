<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container" style="padding-top: 20px">
    <div class="row" style="padding-bottom: 20px">
        <div class="col-10">
            <b class="text-left font-family:comfortaa " style="font-size: 26px">${titolo}</b>
        </div>
        <div class="col-2">
            <sec:authorize access="hasRole('ADMIN')">
            <a href="newCar" class="btn btn-xs btn-success">Inserisci auto</a>
            </sec:authorize>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered table-dark ">
            <thead>
            <tr>
                <sec:authorize access="hasRole('ADMIN')">
                    <th scope="col"></th>
                    <th scope="col"></th>
                </sec:authorize>
                <th scope="col">Auto</th>
                <th scope="col">Targa</th>
                <th scope="col">Anno</th>
                <th scope="col">Tipo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th style="width: 5%">
                            <form action="viewCar" method="get">
                                <input type="text" name="carId" value="${car.id}" hidden>
                                <button type="submit" class="btn btn-secondary">
                                    <i class="bi bi-pencil-square"></i>
                                </button>
                            </form>
                        </th>
                        <th style="width: 5%">
                            <form action="deleteCar" method="get">
                                <input type="text" name="carId" value="${car.id}" hidden>
                                <button type="submit" class="btn btn-danger">
                                    <i class="bi bi-trash3"></i>
                                </button>
                            </form>
                        </th>
                    </sec:authorize>
                    <th>${car.model}</th>
                    <td>${car.licensePlate}</td>
                    <td>${car.year}</td>
                    <td>${car.type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
