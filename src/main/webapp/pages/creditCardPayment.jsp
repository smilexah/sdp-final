<%--
  Created by IntelliJ IDEA.
  User: Muratbekuly Meyirjan
  Date: 13/12/2023
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdu.edu.kz.adapter.CreditCardPayment" %>
<html>
<head>
    <title>Credit Card Payment</title>
</head>
<body>
<h2>Credit Card Payment Form</h2>
<form action="creditCard" method="post">
    <label for="amount">Enter Amount in KZT:</label>
    <input type="text" id="amount" name="amount"><br><br>
    <input type="submit" value="Pay">
</form>
</body>
</html>
