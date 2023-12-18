<%--
  Created by IntelliJ IDEA.
  User: Muratbekuly Meyirjan
  Date: 15/12/2023
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SDP - Login Page</title>
</head>
<body>
<form action="login" method="post">
    <table style="background-color: #585893; padding: 20px">
        <tr>
            <td>
                <% if (request.getAttribute("message") != null) { %>
                <h3 style="color: red">${message}</h3>
                <% } %>
                <% if (request.getAttribute("successMessage") != null) { %>
                <h3 style="color: green">${successMessage}</h3>
                <% } %>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>LogIn!</td>
            <td></td>
        </tr>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value="login"></td>
            <td><a href="registration.jsp" style="text-decoration: none; color: azure">Registration</a></td>
        </tr>
    </table>
</form>
</body>
</html>
