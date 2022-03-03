<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">

    <center><h1>${titolo}</h1></center>

    <form action="searchCar" method="GET">
        <div class="row">
            <div class="col">
                <label>Data Inizio:</label>
                <input type="date" name="dateFrom" class="form-control border-success" required>
            </div>
            <div class="col">
                <label>Data Consegna:</label>
                <input type="date" name="dateTo" class="form-control border-success" required>
            </div>
            <div class="col-3">
                <label>*</label>
                <button type="submit" class="btn btn-primary form-control">${button_verify}</button>
            </div>
        </div>
    </form>


    <hr>

    <form:form action="saveReservation" modelAttribute="reservation" method="POST">
        ${dateFromSelect}
        <form:input path="endDate" type="date" cssClass="form-control border-success"/>
        <form:input path="endDate" type="date" cssClass="form-control border-success"/>
        <center><b>${messageSelect}</b></center>
    <div class="row">
        <table class="table table-bordered table-dark ">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Auto</th>
                <th scope="col">Targa</th>
                <th scope="col">Anno</th>
                <th scope="col">Tipo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <th style="width: 5%">
                        <form:input path="user" type="number"  cssClass="form-control border-success"/>
                        <form:checkbox path="car" type="number" cssClass="form-control border-success"/>
                    </th>
                    <th>${car.model}</th>
                    <td>${car.licensePlate}</td>
                    <td>${car.year}</td>
                    <td>${car.type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>*</label>
            <input type="submit" class="form-control btn btn-success" value="${button_ok_text}" ${button_ok_show}>
        </div>
    </div>
    </form:form>
</div>

</div>
