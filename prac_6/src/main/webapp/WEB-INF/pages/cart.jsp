<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="en">
    <head>
        <title>Cart</title>

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
        </style>
    </head>
    <body>
        <a href="/clearCart">Clear cart</a>
        <a href="/makeOrder">Make order</a>

        <h1>Cart</h1>

        <table>
            <tr>
                <th>name</th>
                <th>type</th>
                <th>cost</th>
                <th>amount</th>
            </tr>
            <c:forEach var="book" items="${bookList}">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.productType}</td>
                    <td>${book.cost}</td>
                    <td>${book.amount}</td>
                </tr>
            </c:forEach>
            <c:forEach var="telephone" items="${telephoneList}">
                <tr>
                    <td>${telephone.model}</td>
                    <td>${telephone.productType}</td>
                    <td>${telephone.cost}</td>
                    <td>${telephone.amount}</td>
                </tr>
            </c:forEach>
            <c:forEach var="washingMachine" items="${washingMachineList}">
                <tr>
                    <td>${washingMachine.manufacturer}, ${washingMachine.tankCapacity}</td>
                    <td>${washingMachine.productType}</td>
                    <td>${washingMachine.cost}</td>
                    <td>${washingMachine.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
