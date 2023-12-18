<%@ page import="com.sdu.edu.kz.booking.Booking" %>
<%@ page import="com.sdu.edu.kz.singleton.HotelManager" %>
<%@ page import="com.sdu.edu.kz.db.DatabaseManager" %>
<%@ page import="com.sdu.edu.kz.factory.Room" %>
<%@ page import="com.sdu.edu.kz.decorator.TransferDecorator" %>
<%@ page import="com.sdu.edu.kz.decorator.BreakfastDecorator" %>
<%@ page import="com.sdu.edu.kz.decorator.ExcursionDecorator" %>
<%@ page import="com.sdu.edu.kz.db.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Hotel Booking</title>
</head>
<body>
<%
  HotelManager hotelManager = HotelManager.getInstance();
  DatabaseManager db = DatabaseManager.getInstance();

  String message = "";
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    try {
      // Extracting form data
      String roomType = request.getParameter("roomType");
      String[] extras = request.getParameterValues("extras");
      int numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
      String userName = request.getParameter("userName");
      String password = request.getParameter("password");
      String clientName = request.getParameter("clientName");
      String paymentType = request.getParameter("paymentType");

      // Here, add your logic to create and configure the room based on the user choice
      Room room = null; // Placeholder for room object
      // Example: if(roomType.equals("family")) { room = new FamilyRoom(); } and so on

      // Decorate the room with selected extras
      if (extras != null) {
        for (String extra : extras) {
          if ("breakfast".equals(extra)) {
            room = new BreakfastDecorator(room);
          } else if ("excursion".equals(extra)) {
            room = new ExcursionDecorator(room);
          } else if ("transfer".equals(extra)) {
            room = new TransferDecorator(room);
          }
        }
      }

      // Handle user registration or retrieval
      User user = db.getUserByName(userName);
      if (user == null) {
        // Register new user
        db.addUser(userName, password);
      }

      // Create a booking object
      // Pricing strategy and payment system can be set based on user's choice
      Booking booking = new Booking(room, /* pricingStrategy */, clientName, /* paymentSystem */, numberOfDays);
      hotelManager.BookRoom(booking);

      // Confirmation message
      message = "Booking Confirmed for " + clientName;
    } catch (Exception e) {
      message = "Error in booking: " + e.getMessage();
    }
  }
%>
<h1>Hotel Booking Form</h1>
<p><%= message %></p>
<form method="post">
  <!-- Room Type Selection -->
  <!-- Extras Selection -->
  <!-- User and Payment Information -->
  <input type="submit" value="Book Room">
</form>
</body>
</html>
