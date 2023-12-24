<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Payment Successful</title>
  <style>
    .center {
      text-align: center;
      margin-top: 50px;
    }
  </style>
</head>
<body>
<div class="center">
  <h2>Payment Successful!</h2>
  <p>Thank you for your payment. Your transaction has been completed successfully.</p>

  <%-- Displaying the payment amount if passed --%>
  <%
    String amount = request.getParameter("amount");
    if (amount != null && !amount.isEmpty()) {
      out.println("<p>Amount Paid: KZT " + amount + "</p>");
    }
  %>

  <a href="index.jsp">Return to Home</a> <!-- Link to the home page -->
  <!-- You can add more links or information as needed -->
</div>
</body>
</html>
