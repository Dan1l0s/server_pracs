<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="en">
<head>
<title>Tables</title>

<style>

table {
    border: 1px solid grey;
    margin-left: auto;
    margin-right: auto;
}

th {
    border: 1px solid grey;
    font-size: 250%;
}

td {
    border: 1px solid grey;
    font-size: 250%;
}

h1 {
    text-align: center;
}

a {
    text-align: center;
    color: black;
}

</style>

</head>
<body>

<h1>
<a href="/cart">Cart</a>
</h1>

<h1>Books</h1>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>author</th>
        <th>productType</th>
        <th>sellerNumber</th>
        <th>cost</th>
        <th>amount</th>
        <th>add</th>
        <th>remove</th>
    </tr>
    <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.productType}</td>
            <td>${book.sellerNumber}</td>
            <td>${book.cost}</td>
            <td>${book.amount}</td>
            <td><a href="/book/add/${book.id}">add</a></td>
            <td><a href="/book/remove/${book.id}">remove</a></td>
        </tr>
    </c:forEach>
</table>

<h1>Clients</h1>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>email</th>
        <th>login</th>
        <th>password</th>
    </tr>
    <c:forEach var="client" items="${clientList}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.email}</td>
            <td>${client.login}</td>
            <td>${client.password}</td>
        </tr>
    </c:forEach>
</table>

<h1>Telephones</h1>
<table>
    <tr>
        <th>id</th>
        <th>model</th>
        <th>manufacturer</th>
        <th>productType</th>
        <th>batteryCapacity</th>
        <th>sellerNumber</th>
        <th>cost</th>
        <th>amount</th>
        <th>add</th>
        <th>remove</th>
    </tr>
    <c:forEach var="telephone" items="${telephoneList}">
        <tr>
            <td>${telephone.id}</td>
            <td>${telephone.model}</td>
            <td>${telephone.manufacturer}</td>
            <td>${telephone.productType}</td>
            <td>${telephone.batteryCapacity}</td>
            <td>${telephone.sellerNumber}</td>
            <td>${telephone.cost}</td>
            <td>${telephone.amount}</td>
            <td><a href="/telephone/add/${telephone.id}">add</a></td>
            <td><a href="/telephone/remove/${telephone.id}">remove</a></td>
        </tr>
    </c:forEach>
</table>



<h1>WashingMachines</h1>
<table>
    <tr>
        <th>id</th>
        <th>manufacturer</th>
        <th>tankCapacity</th>
        <th>productType</th>
        <th>sellerNumber</th>
        <th>cost</th>
        <th>amount</th>
        <th>add</th>
        <th>remove</th>

    </tr>
    <c:forEach var="washingMachine" items="${washingMachineList}">
        <tr>
            <td>${washingMachine.id}</td>
            <td>${washingMachine.manufacturer}</td>
            <td>${washingMachine.tankCapacity}</td>
            <td>${washingMachine.productType}</td>
            <td>${washingMachine.sellerNumber}</td>
            <td>${washingMachine.cost}</td>
            <td>${washingMachine.amount}</td>
            <td><a href="/washingMachine/add/${washingMachine.id}">add</a></td>
            <td><a href="/washingMachine/remove/${washingMachine.id}">remove</a></td>
        </tr>
    </c:forEach>
</table>



</body>
</html>