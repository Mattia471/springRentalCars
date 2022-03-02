<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
<center><h1>${titolo}</h1></center>
<form:form action="saveCustomer" modelAttribute="customer"  method="post">
    <div class="row">
        <div class="col">
            <label>Nome:</label>
            <form:input path="name" type="text" id="name" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>Cognome:</label>
            <form:input path="surname" type="text" id="surname"  cssClass="form-control border-success"/>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Email:</label>
            <form:input path="email" type="email"  id="email" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>Password:</label>
            <form:input path="password" type="password" id="email" cssClass="form-control border-success"/>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Data di nascita:</label>
            <form:input path="birthdate"  type="date" id="birthdate" cssClass="form-control border-success"/>
        </div>
        <div class="col">
            <label>*</label>
            <form:input path="id" id="id" type="number"/>
            <input type="submit" class="form-control btn btn-success" value="Crea utente">
        </div>
    </div>
    </div>
</form:form>

</div>
