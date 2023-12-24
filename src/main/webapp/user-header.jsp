<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<% HttpSession httpSession = request.getSession();
    String userFirstName = (String) httpSession.getAttribute("userFirstName"); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Header</title>
    <link rel="stylesheet" href="./css/booking_style.css">
    <style>
        .dropdown {
            float: left;
            overflow: hidden;
        }

        .dropdown .dropbtn {
            border: none;
            outline: none;
            background-color: inherit;
            font-family: inherit;
            margin: 0;

            font-weight: 500;
            color: var(--text-light);
            transition: 0.3s;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
</head>
<body>
<nav>
    <ul class="nav__links">
        <li class="link"><a href="./index.jsp">Home</a></li>
        <li class="link"><a href="./booking.jsp">Book</a></li>
        <li class="link"><a href="./contact.jsp">Contacts</a></li>
    </ul>
    <div class="nav__logo"><a href="./index.jsp">SDU Hotel</a></div>
    <ul class="nav__links">
        <li class="dropdown">
            <a href="javascript:void(0)" class="dropbtn">Hi, <%
                out.println(userFirstName); %>!
            </a>
            <div class="dropdown-content">
                <a href="./my-profile.jsp" class="zmdi zmdi-account material-icons-name">Account</a>
                <a href="./main.jsp" class="zmdi zmdi-view-dashboard material-icons-name">My Bookings</a>
                <a href="./sign-out.jsp" class="zmdi zmdi-sign-in material-icons-name">Sign Out</a>
            </div>
        </li>
    </ul>
</nav>
</body>
</html>
