<%-- 
    Document   : user header
    Created on : Jan 27, 2022, 1:47:52 AM
    Author     : sayur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Header</title>
    <style>
        ul:last-child {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a, .dropbtn {
            display: inline-block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover, .dropdown:hover .dropbtn {
            background-color: red;
        }

        li.dropdown {
            display: inline-block;
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
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
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
            <a href="javascript:void(0)" class="dropbtn"><% if (request.getAttribute("userFirstName") != null) { %>
                    <span> Hi, <%= request.getAttribute("userFirstName") %>!</span>
                <% } %></a>
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
