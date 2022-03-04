<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container" style="padding-top: 20px">
    <div class="row" style="padding-bottom: 20px">
        <div class="col-10">
            <b class="text-left font-family:comfortaa " style="font-size: 26px">${titolo}</b>
        </div>
        <div class="col-2">
            <a href="newReservation" class="btn btn-xs btn-success" ${noShow}>Noleggia Auto</a>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered table-dark ">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col"></th>
                <th scope="col">Auto</th>
                <th scope="col">Inizio Noleggio</th>
                <th scope="col">Fine Noleggio</th>
                <th scope="col">Stato</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reservation" items="${reservations}">
                <!--tramite parseNumber assegno ad una variabile differenceDays la differenza tra la data di inizio noleggio e la data di oggi passata da servlet now diviso per (secondi*minuti*ore)/1000 ottenendo i millisecondi-->
                <fmt:parseNumber var="differenceDays" value="${((reservation.startDate.time - now.time) / (1000*60*60*24)) /10 }" />
                <tr>
                    <c:choose>
                        <c:when test="${user.isAdmin}">
                            <th style="width: 5%">
                                <form action="declineReservation" method="get">
                                    <input type="text" name="reservationId" value="${reservation.id}" hidden>
                                    <button type="submit" class="btn btn-danger">
                                        <i class="bi bi-x"></i>
                                    </button>
                                </form>
                            </th>
                            <th style="width: 5%">
                                <form action="approveReservation" method="get">
                                    <input type="text" name="reservationId" value="${reservation.id}" hidden>
                                    <button type="submit" class="btn btn-success">
                                        <i class="bi bi-check-lg"></i>
                                    </button>
                                </form>
                            </th>
                        </c:when>
                        <c:otherwise>

                            <c:choose>
                            <c:when test="${differenceDays >= 2}"> <!--CONTROLLO SE I GIORNI RIMANENTI SONO PIU' O MENO DI 2-->
                                <th style="width: 5%">
                                    <form action="editReservation" method="get">
                                        <input type="text" name="reservationId" value="${reservation.id}" hidden>
                                        <input type="text" name="dateFrom" value="${reservation.startDate}" hidden>
                                        <input type="text" name="dateTo" value="${reservation.endDate}" hidden>
                                        <button type="submit" class="btn btn-secondary">
                                            <i class="bi bi-pencil-square"></i>
                                        </button>
                                    </form>
                                </th>
                                <th style="width: 5%">
                                    <form action="deleteReservation" method="get">
                                        <input type="text" name="reservationId" value="${reservation.id}" hidden>
                                        <button type="submit" class="btn btn-danger">
                                            <i class="bi bi-trash3"></i>
                                        </button>
                                    </form>
                                </th>
                            </c:when>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>
                    <td class="bg-success">${reservation.car.model}</td>
                    <td>${reservation.startDate}</td>
                    <td>${reservation.endDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${reservation.status=='CONFERMATA'}">
                                <span class="badge badge-success">${reservation.status}</span></td>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger">${reservation.status}</span></td>
                            </c:otherwise>
                        </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
