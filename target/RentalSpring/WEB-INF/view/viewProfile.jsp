<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
<center><h1>${titolo}</h1></center>
<form:form action="saveCustomer" modelAttribute="customer" method="post">
    <div class="row">
        <div class="col">
            <label>Nome:</label>
            <input type="text" name="name" class="form-control border-success" value="${customer.name}">
        </div>
        <div class="col">
            <label>Cognome:</label>
            <input type="text" name="surname" class="form-control border-success" value="${customer.surname}">
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col">
            <label>Email:</label>
            <input type="email" name="email" class="form-control border-success" value="${customer.email}">
        </div>
        <div class="col">
            <label>Password:</label>
            <input type="password" name="password" class="form-control border-success" value="${customer.password}">
        </div>
    </div>

    <hr>

    <div class="row">

        <div class="col">
            <label>*</label>
            <input type="submit" class="form-control btn btn-success" value="${button}">
        </div>
    </div>
    </div>
</form:form>

</div>
