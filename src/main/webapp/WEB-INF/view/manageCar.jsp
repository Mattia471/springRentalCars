<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
<center><h1>${titolo}</h1></center>
<form:form action="${formAction}" modelAttribute="car" method="post">
    <div class="row">
        <div class="col">
            <label>Targa:</label>
            <input type="text" name="licensePlate" class="form-control border-success"
                <c:if test = "${car.licensePlate!=null}">value="${car.licensePlate}" </c:if> placeholder="Targa auto">
        </div>
        <div class="col">
            <label>Produttore:</label>
            <input type="text" name="manufacturer" class="form-control border-success"
                   <c:if test = "${car.manufacturer!=null}">value="${car.manufacturer}" </c:if> placeholder="Produttore auto">
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Modello:</label>
            <input type="text" name="model" class="form-control border-success"
                   <c:if test = "${car.model!=null}">value="${car.model}" </c:if> placeholder="Modello auto">
        </div>
        <div class="col">
            <label>Tipo:</label>
            <input type="text" name="type" class="form-control border-success"
                   <c:if test = "${car.type!=null}">value="${car.type}" </c:if> placeholder="Tipo auto">
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Anno:</label>
            <input type="text" name="year" class="form-control border-success"
                   <c:if test = "${car.year!=null}">value="${car.year}" </c:if> placeholder="Anno auto">
        </div>
        <div class="col">
            <label>*</label>
            <input type="submit" class="form-control btn btn-success" value="Inserisci Auto">
        </div>
    </div>
    </div>
</form:form>

</div>
