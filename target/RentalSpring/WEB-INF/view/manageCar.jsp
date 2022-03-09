<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
<center><h1>${titolo}</h1></center>
<form:form action="saveCar" modelAttribute="car" method="post">
    <div class="row">
        <div class="col">
            <label>Targa:</label>
            <form:input path="licensePlate" type="text" id="licensePlate" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>Produttore:</label>
            <form:input path="manufacturer" type="text" id="manufacturer" cssClass="form-control border-success"/>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Modello:</label>
            <form:input path="model" type="text" id="model" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>Tipo:</label>
            <form:input path="type" type="text" id="type" cssClass="form-control border-success"/>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Anno:</label>
            <form:input path="year" type="text" id="year" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>*</label>
            <form:input path="id" id="id" type="hidden"/>
            <input type="submit" class="form-control btn btn-success" value="Inserisci Auto">
        </div>
    </div>
    </div>
</form:form>

</div>
