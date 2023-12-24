<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdu.edu.kz.booking.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
        }
        .booking-table {
            width: 100%;
            border-collapse: collapse;
        }
        .booking-table th, .booking-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .booking-table th {
            background-color: #4CAF50;
            color: white;
        }
        .booking-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>My Bookings</h2>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("userBookings");
    if (bookings == null || bookings.isEmpty()) {
        out.println("<p>No bookings found.</p>");
    } else {
%>
<table class="booking-table">
    <tr>
        <th>Booking ID</th>
        <th>Client Name</th>
        <th>Room Description</th>
        <th>Number of Days</th>
        <th>Price</th>
    </tr>
    <%
        for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getId() %></td>
        <td><%= booking.getClient() %></td>
        <td><%= booking.getDescription() %></td>
        <td><%= booking.getDays() %></td>
        <td><%= booking.getPrice() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
