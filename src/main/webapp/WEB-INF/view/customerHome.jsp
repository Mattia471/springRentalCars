<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container" style="padding-top: 20px">
    <div class="row" style="padding-bottom: 20px">
        <div class="col-10">
            <b class="text-left font-family:comfortaa " style="font-size: 26px">${titolo}</b>
        </div>
        <div class="col-2">
            <a href="newReservation" class="btn btn-xs btn-success">Noleggia Auto</a>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered table-dark ">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col"></th>
                <th scope="col">Data</th>
                <th scope="col">A</th>
                <th scope="col">Stato</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reservation" items="${reservations}">
                <tr>
                    <th style="width: 5%">
                        <form action="viewProfile" method="get">
                            <input type="text" name="customerId" value="${reservation.id}" hidden>
                            <button type="submit" class="btn btn-secondary">
                                <i class="bi bi-pencil-square"></i>
                            </button>
                        </form>
                    </th>
                    <th style="width: 5%">
                        <form action="deleteCustomer" method="get">
                            <input type="text" name="customerId" value="${reservation.id}" hidden>
                            <button type="submit" class="btn btn-danger">
                                <i class="bi bi-trash3"></i>
                            </button>
                        </form>
                    </th>
                    <th>${reservation.startDate}</th>
                    <td>${reservation.endDate}</td>
                    <td><span class="badge badge-success">${reservation.status}</span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
