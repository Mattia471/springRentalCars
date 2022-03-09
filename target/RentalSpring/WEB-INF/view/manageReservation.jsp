<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">

    <center><h1>${titolo}</h1></center>

    <form action="searchCar" method="GET">
        <div class="row">
            <div class="col">
                <label>Data Inizio:</label>
                <input type="date" name="dateFrom" class="form-control border-success"
                        <c:if test="${dateFromSelect!=null}">value="${dateFromSelect}" </c:if> required>
            </div>
            <div class="col">
                <label>Data Consegna:</label>
                <input type="date" name="dateTo" class="form-control border-success"
                       <c:if test="${dateToSelect!=null}">value="${dateToSelect}" </c:if> required>
            </div>
            <div class="col-3">
                <label>*</label>
                <c:choose>
                    <c:when test="${reservationId!=null}">
                        <input type="text" name="reservationId" class="form-control border-success" value="${reservationId}" hidden>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="reservationId" class="form-control border-success" value="0" hidden>
                    </c:otherwise>
                </c:choose>
                <button type="submit" class="btn btn-primary form-control">${button_verify}</button>
            </div>
        </div>
    </form>

    <hr>
    <hr>


    <form:form action="saveReservation" modelAttribute="addReservation" method="post">
        <input name="startDate" value="${dateFromSelect}" hidden>
        <input name="endDate" value="${dateToSelect}" hidden >
        <input value="33" name="userId" hidden> <!--DA MODIFICARE CON ID DELL'UTENTE APPENA SARA' POSSIBILE-->

        <c:choose>
            <c:when test="${reservationId!=null}">
                <input type="text" name="reservationId" class="form-control border-success" value="${reservationId}" hidden>
            </c:when>
            <c:otherwise>
                <input type="text" name="reservationId" class="form-control border-success" value="0" hidden>
            </c:otherwise>
        </c:choose>
        <center><b style="font-size: 20px">${messageSelect}</b></center>
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
                            <input type="checkbox" value="${car.id}" name="carId">
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


        <div class="row">
            <div class="col">
                <label>*</label>
                <input type="submit" class="form-control btn btn-success" value="${button_ok_text}" ${button_ok_show}>
            </div>
        </div>

    </form:form>

</div>
