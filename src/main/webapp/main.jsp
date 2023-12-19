<%--
  Created by IntelliJ IDEA.
  User: Muratbekuly Meyirjan
  Date: 15/12/2023
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome - SDP</title>
</head>
<body>

<% if (request.getAttribute("messageUsername") != null) { %>
<div style="color: red;">
    <h1> Hi <%= request.getAttribute("messageUsername") %> ! Welcome to our website! </h1>
</div>
<% } %>

</body>
</html>
